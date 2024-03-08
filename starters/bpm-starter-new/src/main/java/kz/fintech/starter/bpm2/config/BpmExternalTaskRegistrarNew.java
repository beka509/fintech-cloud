package kz.fintech.starter.bpm2.config;

import kz.fintech.starter.bpm2.annotations.BpmExternalTaskContainerNew;
import kz.fintech.starter.bpm2.annotations.EnableBpmExternalTasksNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;
import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_TYPE;

@Slf4j
public class BpmExternalTaskRegistrarNew implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private Environment environment;
    private ResourceLoader resourceLoader;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(BpmExternalTaskContainerNew.class);
        scanner.addIncludeFilter(annotationTypeFilter);
        Set<String> basePackages = getBasePackages(metadata);
        for (String basePackage : basePackages) {
            log.info("Base package: " + basePackage);
            Set<BeanDefinition> candidateComponents = scanner
                    .findCandidateComponents(basePackage);
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    registerExternalTaskContainer(registry, (AnnotatedBeanDefinition) candidateComponent);
                }
            }
        }
        registerExternalTaskClient(registry);
    }

    private void registerExternalTaskClient(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(BpmExternalTaskClientNewFactoryBean.class)
                .setScope(SCOPE_SINGLETON)
                .setAutowireMode(AUTOWIRE_BY_TYPE)
                .setLazyInit(false);
        String beanName = BeanDefinitionReaderUtils.generateBeanName(definition.getBeanDefinition(), registry);
        BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(definition.getBeanDefinition(), beanName), registry);
        log.info("Registered external task client bean " + beanName + " of class " + BpmExternalTaskClientNewFactoryBean.class);
    }

    private void registerExternalTaskContainer(BeanDefinitionRegistry registry, AnnotatedBeanDefinition candidateComponent) {
        AnnotationMetadata annotationMetadata = candidateComponent.getMetadata();
        Assert.isTrue(annotationMetadata.isConcrete(), "@BpmExternalTaskContainer can only be specified on a concrete class");
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(annotationMetadata.getClassName());
        definition.setScope(SCOPE_SINGLETON);
        definition.setAutowireMode(AUTOWIRE_BY_TYPE);
        String beanName = BeanDefinitionReaderUtils.generateBeanName(definition.getBeanDefinition(), registry);
        BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(definition.getBeanDefinition(), beanName), registry);
        log.info("Registered task container bean " + beanName + " of class " + annotationMetadata.getClassName());
    }

    private Set<String> getBasePackages(AnnotationMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(EnableBpmExternalTasksNew.class.getCanonicalName());
        Set<String> basePackages = new HashSet<>();
        if (attributes != null) {
            for (Class cls : (Class[]) attributes.get("value")) {
                ClassUtils.getPackageName(cls);
            }
            for (String pkg : (String[]) attributes.get("basePackages")) {
                if (StringUtils.hasText(pkg)) {
                    basePackages.add(pkg);
                }
            }
            for (Class<?> clazz : (Class[]) attributes.get("basePackageClasses")) {
                basePackages.add(ClassUtils.getPackageName(clazz));
            }
        }
        if (basePackages.isEmpty()) {
            basePackages.add(ClassUtils.getPackageName(metadata.getClassName()));
        }
        return basePackages;
    }

    private ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }
}
