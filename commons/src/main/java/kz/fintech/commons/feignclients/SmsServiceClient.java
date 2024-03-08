package kz.fintech.commons.feignclients;

import kz.fintech.models.whatsApp.SendMessagesRequest;
import kz.fintech.models.whatsApp.SendRequestResponse;
import kz.fintech.models.whatsApp.ViewRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = ServiceNames.SMS_SERVICE, url = ServiceUrls.SMS_SERVICE)
public interface SmsServiceClient {
    @PostMapping(value = "/whatsApp/send/messages")
    SendRequestResponse sendMessages(@RequestBody SendMessagesRequest request);

    @GetMapping("/whatsApp/view/messages/{phoneNumber}")
    ViewRequestResponse viewMessages(@PathVariable("phoneNumber") String phoneNumber);
}
