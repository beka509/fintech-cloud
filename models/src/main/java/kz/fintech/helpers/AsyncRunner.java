package kz.fintech.helpers;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncRunner {

    private final ExecutorService executorService;
    private final LinkedBlockingQueue<Callable<Object>> tasks;

    public AsyncRunner(int threads, String threadPrefix) {
        executorService = Executors.newFixedThreadPool(threads, new DefaultThreadFactory(threadPrefix));
        tasks = new LinkedBlockingQueue<>(threads);
    }

    @SneakyThrows
    public void execute(Runnable task) {
        Callable<Object> callable = Executors.callable(task);
        boolean isAccepted = tasks.offer(callable);
        if (isAccepted) return;
//        executorService.invokeAllr(tasks);
        tasks.clear();
        tasks.add(callable);
    }

    @SneakyThrows
    public void flush() {
        if (!tasks.isEmpty()) executorService.invokeAll(tasks);
        tasks.clear();
    }
}
