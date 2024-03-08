package kz.fintech.controllers;

import kz.fintech.entities.CdrGeneralEntity;
import kz.fintech.models.call.*;
import kz.fintech.services.CallServices;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/call")
@Slf4j
public class CallController {
    private final CallServices callServices;
    @Autowired
    public CallController(CallServices callServices) {
        this.callServices = callServices;
    }

    @GetMapping("/cdr/view-all")
    public List<CdrGeneral> getAllCallData() {
        return callServices.getAllCallData();
    }

    @GetMapping("/cdr/get-by-phone-src-num/{srcNum}")
    public List<CdrGeneral> getByPhoneSrcNum(@PathVariable("srcNum") String srcNum) {
        return callServices.getByPhoneSrcNum(srcNum);
    }

    @GetMapping("/cdr/get-by-phone-src-num-all-param/{srcNum}")
    public List<CdrGeneralClass> getByPhoneSrcNumAllParam(@PathVariable("srcNum") String srcNum) {
        return callServices.getByPhoneSrcNumAllParam(srcNum);
    }

    @GetMapping("/cdr/get-by-phone-dst-num/{dstNum}")
    public List<CdrGeneral> getByPhoneDstNum(@PathVariable("dstNum") String dstNum) {
        return callServices.getByPhoneDstNum(dstNum);
    }

    @GetMapping("/cdr/get-by-phone-dst-num-all-param/{dstNum}")
    public List<CdrGeneralClass> getByPhoneDstNumAllParam(@PathVariable("dstNum") String dstNum) {
        return callServices.getByPhoneDstNumAllParam(dstNum);
    }

    @GetMapping("/cdr/get-by-phone-did/{did}")
    public List<CdrGeneralClass> getByPhoneDid(@PathVariable("did") String did) {
        return callServices.getByPhoneDid(did);
    }

    @PostMapping("/cdr/save-call-by-all")
    public List<CdrGeneralClass> saveCallByAll() {
        return callServices.saveCallByAll();
    }

    @PostMapping("/cdr/save-call-by-src-num/{srcNum}")
    public List<CdrGeneralClass> saveCallBySrcNum(@PathVariable("srcNum") String srcNum) {
        return callServices.saveCallBySrcNum(srcNum);
    }

    @PostMapping("/make-call")
    public MakeCallResponse makeCall(@RequestBody MakeCallRequest request) {
        return callServices.makeCall(request);
    }

    @PostMapping("/check-call")
    public CheckCallResponse checkCall(@RequestBody CheckCallRequest request) {
        return callServices.checkCall(request);
    }

    @GetMapping("/call-status/{phoneNumber}")
    public CallStatus callStatus(@PathVariable("phoneNumber") String phoneNumber) {
        return callServices.callStatus(phoneNumber);
    }

    @PostMapping("/add-call-history-by-phoneNumber")
    public List<CdrGeneralClass> addCallHistory(@RequestBody CallHistoryRequest request) {
        return callServices.addCallHistory(request);
    }

    @PostMapping("/call")
    public String call() {
        return callServices.call();
    }
}
