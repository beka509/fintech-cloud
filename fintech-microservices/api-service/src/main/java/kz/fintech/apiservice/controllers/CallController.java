package kz.fintech.apiservice.controllers;

import io.swagger.annotations.Api;
import kz.fintech.commons.feignclients.CallServiceClient;
import kz.fintech.models.call.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
@Slf4j
@Api(value = "api", description = "API для call-service")
public class CallController {
    private final CallServiceClient callServiceClient;

    public CallController(CallServiceClient callServiceClient) {
        this.callServiceClient = callServiceClient;
    }

    @GetMapping("/cdr/view-all")
    public List<CdrGeneral> getAllCallData() {
        return callServiceClient.getAllCallData();
    }

    @GetMapping("/cdr/get-by-phone-src-num/{srcNum}")
    public List<CdrGeneral> getByPhoneSrcNum(@PathVariable("srcNum") String srcNum) {
        return callServiceClient.getByPhoneSrcNum(srcNum);
    }

    @GetMapping("/cdr/get-by-phone-src-num-all-param/{srcNum}")
    public List<CdrGeneralClass> getByPhoneSrcNumAllParam(@PathVariable("srcNum") String srcNum) {
        return callServiceClient.getByPhoneSrcNumAllParam(srcNum);
    }

    @GetMapping("/cdr/get-by-phone-dst-num/{dstNum}")
    public List<CdrGeneral> getByPhoneDstNum(@PathVariable("dstNum") String dstNum) {
        return callServiceClient.getByPhoneDstNum(dstNum);
    }

    @GetMapping("/cdr/get-by-phone-dst-num-all-param/{dstNum}")
    public List<CdrGeneralClass> getByPhoneDstNumAllParam(@PathVariable("dstNum") String dstNum) {
        return callServiceClient.getByPhoneDstNumAllParam(dstNum);
    }

    @GetMapping("/cdr/get-by-phone-did/{did}")
    public List<CdrGeneralClass> getByPhoneDid(@PathVariable("did") String did) {
        return callServiceClient.getByPhoneDid(did);
    }

    @PostMapping("/cdr/save-call-by-all")
    public List<CdrGeneralClass> saveCallByAll() {
        return callServiceClient.saveCallByAll();
    }

    @PostMapping("/cdr/save-call-by-src-num/{srcNum}")
    public List<CdrGeneralClass> saveCallBySrcNum(@PathVariable("srcNum") String srcNum) {
        return callServiceClient.saveCallBySrcNum(srcNum);
    }

    @PostMapping("/make-call")
    public MakeCallResponse makeCall(@RequestBody MakeCallRequest request) {
        return callServiceClient.makeCall(request);
    }

    @PostMapping("/check-call")
    public CheckCallResponse checkCall(@RequestBody CheckCallRequest request) {
        return callServiceClient.checkCall(request);
    }

    @GetMapping("/call-status-asterisk/{phoneNumber}")
    public CallStatus callStatus(@PathVariable("phoneNumber") String phoneNumber) {
        return callServiceClient.callStatus(phoneNumber);
    }

    @PostMapping("/add-call-history-by-phoneNumber")
    public List<CdrGeneralClass> addCallHistory(@RequestBody CallHistoryRequest request) {
        return callServiceClient.addCallHistory(request);
    }

    @GetMapping("/call")
    public String call() {
        return callServiceClient.call();
    }
}
