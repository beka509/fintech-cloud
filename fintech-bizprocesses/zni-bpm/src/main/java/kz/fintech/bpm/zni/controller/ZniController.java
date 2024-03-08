package kz.fintech.bpm.zni.controller;

import kz.fintech.bpm.zni.service.ZniService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZniController {


    private final ZniService bpmnService;

    public ZniController(ZniService bpmnService){
        this.bpmnService = bpmnService;
    }

    @RequestMapping("/userTask/complete/{taskId}")
    public void completeTask(@PathVariable("taskId") String taskId) {
        bpmnService.completeTask(taskId);
    }
}