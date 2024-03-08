package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.services.CallService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callServicel;

    public CallController(CallService callServicel) {
        this.callServicel = callServicel;
    }
}
