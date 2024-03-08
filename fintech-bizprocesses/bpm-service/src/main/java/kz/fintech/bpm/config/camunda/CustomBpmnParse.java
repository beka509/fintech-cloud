package kz.fintech.bpm.config.camunda;

import org.camunda.bpm.engine.impl.bpmn.behavior.ExternalTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParser;
import org.camunda.bpm.engine.impl.core.variable.mapping.value.ParameterValueProvider;
import org.camunda.bpm.engine.impl.el.ElValueProvider;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class CustomBpmnParse extends BpmnParse {

    public CustomBpmnParse(BpmnParser parser) {
        super(parser);
    }

    @Override
    protected void parseExternalServiceTask(ActivityImpl activity, Element serviceTaskElement) {
        activity.setScope(true);

        String processDefinitionKey = ((ProcessDefinitionEntity) activity.getProcessDefinition()).getKey();
        String topicAttributeValue = serviceTaskElement.attributeNS(CAMUNDA_BPMN_EXTENSIONS_NS, PROPERTYNAME_EXTERNAL_TASK_TOPIC);
        ParameterValueProvider topicNameProvider = new ElValueProvider(expressionManager.createExpression(processDefinitionKey + "_" + topicAttributeValue));
        ParameterValueProvider priorityProvider = parsePriority(serviceTaskElement, PROPERTYNAME_TASK_PRIORITY);
        activity.setActivityBehavior(new ExternalTaskActivityBehavior(topicNameProvider, priorityProvider));
    }

    @Override
    protected ParameterValueProvider parseTopic(Element element, String topicAttribute) {
        String topicAttributeValue = element.attributeNS(CAMUNDA_BPMN_EXTENSIONS_NS, topicAttribute);
        if (topicAttributeValue == null) {
            addError("External tasks must specify a 'topic' attribute in the camunda namespace", element);
            return null;
        } else {
            Expression expression = expressionManager.createExpression(topicAttributeValue);
            return new ElValueProvider(expression);
        }
    }
}
