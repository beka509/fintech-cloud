package kz.fintech.bpm.config;

import kz.fintech.bpm.config.camunda.CustomSpringProcessEngineConfiguration;
import lombok.val;
import org.camunda.bpm.engine.impl.cfg.CompositeProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.util.CamundaSpringBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
public class CamundaConfig {

    @Bean
    public CommandLineRunner processEngineConfigurationImpl(@Value("${camunda.lockTimeInMillis:600000}") int lockTimeInMillis,
                                                            JobExecutor jobExecutor) {
        return args -> jobExecutor.setLockTimeInMillis(lockTimeInMillis);
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(List<ProcessEnginePlugin> processEnginePlugins) {
        val configuration = CamundaSpringBootUtil.initCustomFields(new CustomSpringProcessEngineConfiguration());
        configuration.setHistoryLevel(HistoryLevel.HISTORY_LEVEL_FULL);
        configuration.getProcessEnginePlugins().add(new CompositeProcessEnginePlugin(processEnginePlugins));
        //configuration.setBatchOperationHistoryTimeToLive("P1Y");
        return configuration;
    }
}
