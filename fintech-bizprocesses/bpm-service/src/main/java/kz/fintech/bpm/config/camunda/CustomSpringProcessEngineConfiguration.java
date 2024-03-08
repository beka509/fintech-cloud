package kz.fintech.bpm.config.camunda;

import lombok.val;
import org.camunda.bpm.engine.impl.bpmn.deployer.BpmnDeployer;
import org.camunda.bpm.engine.impl.cfg.DefaultBpmnParseFactory;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;

public class CustomSpringProcessEngineConfiguration extends SpringProcessEngineConfiguration {
    @Override
    protected BpmnDeployer getBpmnDeployer() {
        BpmnDeployer bpmnDeployer = new BpmnDeployer();
        bpmnDeployer.setExpressionManager(expressionManager);
        bpmnDeployer.setIdGenerator(idGenerator);

        if (bpmnParseFactory == null) {
            bpmnParseFactory = new DefaultBpmnParseFactory();
        }

        val bpmnParser = new CustomBpmnParser(expressionManager, bpmnParseFactory);

        if (preParseListeners != null) {
            bpmnParser.getParseListeners().addAll(preParseListeners);
        }
        bpmnParser.getParseListeners().addAll(getDefaultBPMNParseListeners());
        if (postParseListeners != null) {
            bpmnParser.getParseListeners().addAll(postParseListeners);
        }

        bpmnDeployer.setBpmnParser(bpmnParser);

        return bpmnDeployer;
    }
}
