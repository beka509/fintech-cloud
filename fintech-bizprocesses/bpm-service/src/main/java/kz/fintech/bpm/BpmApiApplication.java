package kz.fintech.bpm;

import lombok.val;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.DigestUtils;

import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@lombok.extern.slf4j.Slf4j
@SpringBootApplication
@EnableProcessApplication
@EnableDiscoveryClient
//@EnableFeignClients(basePackageClasses = Default.class)
public class BpmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpmApiApplication.class);
    }

    //@Bean
    CommandLineRunner test1(RuntimeService runtimeService, RepositoryService repositoryService, HistoryService historyService) {
        return args -> {
            /*System.out.println("RESTARTING");
            Map<String, Long> map = historyService.createHistoricActivityInstanceQuery().activityId("BoundaryEvent_0ew9s3u").list().stream()
                    .collect(Collectors.groupingBy(HistoricActivityInstance::getProcessInstanceId, Collectors.counting()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\mortgage.csv"));

            historyService.createHistoricVariableInstanceQuery().processDefinitionKey("MortgageLoanProcess").variableName("request").list()
                    .forEach(v -> {
                        MortgageLoanRequest request = (MortgageLoanRequest) v.getValue();
                        try {
                            writer.write(request.getRequest().getRequestNumber() + ";" + request.getInitiator() + ";" + request.getRequest().getStartTime()
                                    + ";"+(map.containsKey(v.getProcessInstanceId()) ? map.get(v.getProcessInstanceId()) : 0) + "\r\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
            writer.close();
            /*MigrationPlan migrationPlan = runtimeService.createMigrationPlan(
                    "MortgageLoanProcess:92:3ced448e-d9f8-11e9-ad3d-dc4a3e3bf716",
                    "MortgageLoanProcess:96:0e402291-e1cd-11e9-b455-00155d3c172c")
                    .mapEqualActivities()
                    .build();
            /*runtimeService.restartProcessInstances("MortgageLoanProcess:93:ae60fc46-dded-11e9-a636-00155d3c172c")
                    .startBeforeActivity("AttachMissingDocuments")
                    .processInstanceIds("3a289f7d-df4c-11e9-998b-00155d3c172c")
                    .execute();*/
            /*runtimeService.newMigration(migrationPlan)
                    .processInstanceIds("9973b1e3-dab6-11e9-8cb4-00155d3c172c")
                    .execute();*/
            /*System.out.println("RESTARTED");
            throw new RuntimeException("STOP");//*/
        };
    }

    /*@Bean
    public CommandLineRunner test(HistoryService historyService, CommonsService commonsService){
        return args->{
            List<HistoricProcessInstance> processInstanceList = historyService.createHistoricProcessInstanceQuery()
                    .processDefinitionKey("MortgageLoanProcess").list();
            processInstanceList.forEach(processInstance->{
                HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("request").singleResult();
                MortgageLoanRequest mortgageLoanRequest = (MortgageLoanRequest) variableInstance.getValue();
                if(mortgageLoanRequest==null)return;
                Request request = mortgageLoanRequest.getRequest();
                request.setProcessInstanceId(processInstance.getId());
                System.out.println(request.getRequestNumber() + ": " + request.getRequestStatus() + ":"+request.getInitiator());
                request.setProductCode(ProductCodes.MORTGAGE);
                request.setStartTime(processInstance.getStartTime());
                request.setEndTime(processInstance.getEndTime());
                //if(StringUtils.isEmpty(request.getInitiator()))request.setInitiator(processInstance.getStartUserId());
                commonsService.setRequestStatus(request, mortgageLoanRequest, request.getRequestStatus());
                commonsService.setRequestStatus(request, mortgageLoanRequest, request.getRequestStatus());
            });*/
            /*Map<String, String> map = new HashMap<>();
            map.put("a8f5d993-ddee-11e9-a5ca-00155d3a0d22", "200000");
            map.put("8a79ad5f-dde5-11e9-a5ca-00155d3a0d22", "600000");
            map.put("5ec61744-ddda-11e9-a5ca-00155d3a0d22", "400000");
            map.put("43e6bbd7-ddd8-11e9-a5ca-00155d3a0d22", "300000");
            map.put("de353482-ddd2-11e9-a5ca-00155d3a0d22", "400000");
            map.put("3108b8f0-ddcd-11e9-a5ca-00155d3a0d22", "300000");
            map.put("3193ff31-ddc1-11e9-a5ca-00155d3a0d22", "300000");

            Map<String, String> map2 = new HashMap<>();
            map2.put("a8f5d993-ddee-11e9-a5ca-00155d3a0d22", "DECLINED");
            map2.put("8a79ad5f-dde5-11e9-a5ca-00155d3a0d22", "DECLINED");
            map2.put("5ec61744-ddda-11e9-a5ca-00155d3a0d22", "DECLINED");
            map2.put("43e6bbd7-ddd8-11e9-a5ca-00155d3a0d22", "STARTED");
            map2.put("de353482-ddd2-11e9-a5ca-00155d3a0d22", "DECLINED");
            map2.put("3108b8f0-ddcd-11e9-a5ca-00155d3a0d22", "STARTED");
            map2.put("3193ff31-ddc1-11e9-a5ca-00155d3a0d22", "STARTED");

            historyService.createHistoricProcessInstanceQuery()
                    .processInstanceIds(map.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toSet()))
                    .list()
                    .forEach(processInstance -> {
                        val processInstanceId = processInstance.getId();
                        System.out.println(processInstanceId);
                        val branch = nriServiceClient.getOrgUnitByCode(map.get(processInstanceId));
                        System.out.println(branch);
                        val request = (MortgageLoanRequest) historyService.createHistoricVariableInstanceQuery()
                                .processInstanceId(processInstanceId)
                                .variableName(BpmConstants.VARIABLE_REQUEST)
                                .singleResult()
                                .getValue();
                        request.setBranch(branch);
                        //val rn = request.getRequest().getRequestNumber();
                        //request.getRequest().setRequestNumber("RZ19-" + branch.getCode() + rn.lastIndexOf("-"));
                        if ("ACTIVE".equalsIgnoreCase(processInstance.getState())) {
                            runtimeService.setVariable(processInstanceId, BpmConstants.VARIABLE_REQUEST, request);
                        }
                        commonsService.setRequestStatus(request.getRequest(), request, map2.get(processInstanceId));
                    });
            throw new RuntimeException("STOP");
        };
    }*/

    //@Bean
    public CommandLineRunner registerBizprocesses(RepositoryService repositoryService, ResourceLoader resourceLoader) {
        return args -> {

            Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath*:bpmn/*.bpmn");
            for (Resource resource : resources) {
                String definitionName = resource.getFilename();
                try {
                    List<Deployment> deployments = repositoryService.createDeploymentQuery()
                            .deploymentName(definitionName)
                            .orderByDeploymentTime()
                            .desc()
                            .list();
                    if (deployments == null) continue;

                    var deploymentExists = false;
                    val newDiagram = Base64.getEncoder().encodeToString(DigestUtils.md5Digest(resource.getInputStream()));

                    for (Deployment deployment : deployments) {
                        String deploymentId = deployment.getId();
                        System.out.println(definitionName + ": " + deploymentId);
                        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).list();
                        for (ProcessDefinition processDefinition : processDefinitions) {
                            try (InputStream processModel = repositoryService.getProcessModel(processDefinition.getId())) {
                                val deployedDiagram = Base64.getEncoder().encodeToString(DigestUtils.md5Digest(processModel));
                                if (deployedDiagram.equals(newDiagram)) {
                                    log.info("BPMN [" + definitionName + "] with deployment id [" + deploymentId + "] exists. Deployed on " + deployment.getDeploymentTime());
                                    deploymentExists = true;
                                    break;
                                }

                            } catch (Exception e) {
                                log.error("Failed deploying BPMN [" + definitionName + "]", e);
                            }
                        }

                    }
                    if (deploymentExists) continue;

                    log.info("Deploying BPMN [" + definitionName + "]");
                    String deploymentId = repositoryService.createDeployment()
                            .name(definitionName)
                            .addInputStream(definitionName, resource.getInputStream())
                            .deploy()
                            .getId();
                    log.info("BPMN [" + definitionName + "] deployed with id [" + deploymentId + "]");
                } catch (Throwable e) {
                    log.error("Failed to deploy BPMN [" + definitionName + "]", e);
                }
            }
        };
    }

    //@Bean
    public CommandLineRunner clr(RuntimeService runtimeService) {
        return args -> {
            System.out.println("STARTING>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            runtimeService.restartProcessInstances("MortgageLoanProcess:54:79df0293-3ff0-11e9-addf-dc4a3e3bf716")
                    .startBeforeActivity("CheckColvirCreditHistoryStopFactors")
                    .processInstanceIds("f0f8aec1-4088-11e9-a1e9-dc4a3e3bf716")
                    .execute();
        };
    }
}
