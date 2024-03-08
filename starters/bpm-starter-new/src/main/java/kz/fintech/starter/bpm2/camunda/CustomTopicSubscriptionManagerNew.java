package kz.fintech.starter.bpm2.camunda;

import kz.fintech.helpers.DefaultThreadFactory;
import kz.fintech.starter.bpm2.config.BpmPropsNew;
import kz.fintech.starter.bpm2.exceptions.CamundaServerNotFoundExceptionNew;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.topic.impl.TopicSubscriptionManager;
import org.camunda.bpm.client.variable.impl.TypedValues;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static kz.fintech.starter.bpm2.utils.HelpersNew.maxTasks;
import static kz.fintech.starter.bpm2.utils.HelpersNew.maxThreads;

@Slf4j
public class CustomTopicSubscriptionManagerNew extends TopicSubscriptionManager {

    private final ThreadPoolExecutor executor;

    CustomTopicSubscriptionManagerNew(EngineClient engineClient,
                                   TypedValues typedValues,
                                   long clientLockDuration,
                                   String processDefinitionKey,
                                   BpmPropsNew props) {
        super(engineClient, typedValues, clientLockDuration);
        int maxThreads = maxThreads(props, processDefinitionKey);
        int maxTasks = maxTasks(props, processDefinitionKey);
        executor = new ThreadPoolExecutor(maxThreads, maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(maxTasks),
                new DefaultThreadFactory(processDefinitionKey));
        log.info("Thread pool executor with {} threads configured for [{}]", maxThreads, processDefinitionKey);
    }

    @Override
    public void run() {
        while (isRunning.get()) {
            try {
                acquire();
            } catch (CamundaServerNotFoundExceptionNew ignored) {
                suspend(10_000);
            } catch (Throwable e) {
                LOG.logError("006", "Exception while acquiring tasks.", e);
            }
        }
    }

    @SneakyThrows
    @Override
    protected void acquire() {
        taskTopicRequests.clear();
        externalTaskHandlers.clear();
        subscriptions.forEach(this::prepareAcquisition);

        if (!taskTopicRequests.isEmpty()) {
            List<ExternalTask> externalTasks = fetchAndLock(taskTopicRequests);
            if (!externalTasks.isEmpty()) {
                CountDownLatch latch = new CountDownLatch(externalTasks.size());
                externalTasks.forEach(externalTask -> executor.execute(() -> {
                    String topicName = externalTask.getTopicName();
                    ExternalTaskHandler taskHandler = externalTaskHandlers.get(topicName);
                    try {
                        if (taskHandler != null) {
                            handleExternalTask(externalTask, taskHandler);
                        } else {
                            LOG.taskHandlerIsNull(topicName);
                        }
                    } catch (Throwable e) {
                        log.error("Failed to execute " + topicName, e);
                    } finally {
                        latch.countDown();
                    }
                }));
                latch.await();
            }
            if (!isBackoffStrategyDisabled.get()) {
                runBackoffStrategy(externalTasks);
            }
        }
    }
}
