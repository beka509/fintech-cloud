package kz.fintech.bpm.config;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.bpmn.parser.DefaultFailedJobParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class NovaJobParseListener extends DefaultFailedJobParseListener {

    @Override
    public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope, ActivityImpl activity) {
        boolean async = true;
        Element extensionElement = serviceTaskElement.element("extensionElements");
        if (extensionElement != null) {
            Element propertiesElement = extensionElement.element("properties");
            if (propertiesElement != null) {
                List<Element> propertyList = propertiesElement.elements("property");
                for (Element property : propertyList) {
                    String name = property.attribute("name");
                    String value = property.attribute("value");
                    async = !("async".equals(name) && "false".equalsIgnoreCase(value));
                }
            }
        }
        if (async) {
            activity.setAsyncBefore(true);
        } else {
            log.info("Not async [id: " + activity.getId() + ", name: " + activity.getName() + "]");
        }
        super.parseServiceTask(serviceTaskElement, scope, activity);
    }
}
