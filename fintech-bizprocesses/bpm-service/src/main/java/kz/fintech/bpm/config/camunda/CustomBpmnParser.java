package kz.fintech.bpm.config.camunda;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParser;
import org.camunda.bpm.engine.impl.cfg.BpmnParseFactory;
import org.camunda.bpm.engine.impl.el.ExpressionManager;

public class CustomBpmnParser extends BpmnParser {
    public CustomBpmnParser(ExpressionManager expressionManager, BpmnParseFactory bpmnParseFactory) {
        super(expressionManager, bpmnParseFactory);
    }
    @Override
    public BpmnParse createParse() {
        return new CustomBpmnParse(this);
    }
}
