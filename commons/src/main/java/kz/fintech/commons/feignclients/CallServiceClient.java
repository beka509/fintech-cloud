package kz.fintech.commons.feignclients;

import kz.fintech.models.call.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = ServiceNames.CALL_SERVICE, url = ServiceUrls.CALL_SERVICE)
public interface CallServiceClient {

    @GetMapping("/call/cdr/view-all")
    List<CdrGeneral> getAllCallData();

    @GetMapping("/call/cdr/get-by-phone-src-num/{srcNum}")
    List<CdrGeneral> getByPhoneSrcNum(@PathVariable("srcNum") String srcNum);

    @GetMapping("/call/cdr/get-by-phone-src-num-all-param/{srcNum}")
    List<CdrGeneralClass> getByPhoneSrcNumAllParam(@PathVariable("srcNum") String srcNum);

    @GetMapping("/call/cdr/get-by-phone-dst-num/{dstNum}")
    List<CdrGeneral> getByPhoneDstNum(@PathVariable("dstNum") String dstNum);

    @GetMapping("/call/cdr/get-by-phone-dst-num-all-param/{dstNum}")
    List<CdrGeneralClass> getByPhoneDstNumAllParam(@PathVariable("dstNum") String dstNum);

    @GetMapping("/call/cdr/get-by-phone-did/{did}")
    List<CdrGeneralClass> getByPhoneDid(@PathVariable("did") String did);

    @PostMapping("/call/cdr/save-call-by-all")
    List<CdrGeneralClass> saveCallByAll();

    @PostMapping("/call/cdr/save-call-by-src-num/{srcNum}")
    List<CdrGeneralClass> saveCallBySrcNum(@PathVariable("srcNum") String srcNum);

    @PostMapping("/call/make-call")
    MakeCallResponse makeCall(@RequestBody MakeCallRequest request);

    @PostMapping("/call/check-call")
    CheckCallResponse checkCall(@RequestBody CheckCallRequest request);

    @GetMapping("/call/call-status/{phoneNumber}")
    CallStatus callStatus(@PathVariable("phoneNumber") String phoneNumber);

    @PostMapping("/call/add-call-history-by-phoneNumber")
    List<CdrGeneralClass> addCallHistory(@RequestBody CallHistoryRequest request);

    @PostMapping("/call/call")
    String call();
}
