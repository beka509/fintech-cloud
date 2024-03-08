package kz.fintech.bpm.zni.service;

import org.camunda.bpm.engine.TaskService;
import org.springframework.stereotype.Service;

@Service
public class ZniService {

    private final TaskService taskService;

    public ZniService(TaskService taskService) {
        this.taskService = taskService;
    }

    public  void completeTask(String taskId){
        taskService.complete(taskId);
    }

}