package kz.fintech.controllers;

import kz.fintech.models.whatsApp.SendMessagesRequest;
import kz.fintech.models.whatsApp.SendRequestResponse;
import kz.fintech.models.whatsApp.ViewRequestResponse;
import kz.fintech.services.WhatsAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whatsApp")
@Slf4j
public class WhatsAppController {
    private final WhatsAppService whatsAppService;

    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/send/messages")
    public SendRequestResponse sendMessages(@RequestBody SendMessagesRequest request) {
        return whatsAppService.sendMessages(request);
    }

    @GetMapping("/view/messages/{phoneNumber}")
    public ViewRequestResponse viewMessages(@PathVariable("phoneNumber") String phoneNumber) {
        return whatsAppService.viewMessages(phoneNumber);
    }
}
