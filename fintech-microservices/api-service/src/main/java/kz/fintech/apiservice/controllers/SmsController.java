package kz.fintech.apiservice.controllers;

import kz.fintech.commons.feignclients.SmsServiceClient;
import kz.fintech.models.whatsApp.SendMessagesRequest;
import kz.fintech.models.whatsApp.SendRequestResponse;
import kz.fintech.models.whatsApp.ViewRequestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@Slf4j
//@Api(value = "api", description = "API для sms-service")
public class SmsController {

    private final SmsServiceClient smsServiceClient;

    @Autowired
    public SmsController(SmsServiceClient smsServiceClient) {
        this.smsServiceClient = smsServiceClient;
    }

  //  @ApiOperation("Отправка смс по whatsApp")
    @PostMapping(value = "/whatsApp/send/messages")
    public SendRequestResponse sendMessages(@RequestBody SendMessagesRequest request) {
        return smsServiceClient.sendMessages(request);
    }

   // @ApiOperation("Получение история переписки whatsApp")
    @GetMapping(value = "/whatsApp/view/messages/{phoneNumber}")
    public ViewRequestResponse viewMessages(@PathVariable("phoneNumber") String phoneNumber) {
        return smsServiceClient.viewMessages(phoneNumber);
    }
}
