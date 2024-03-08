package kz.fintech.services;

import kz.fintech.models.whatsApp.SendMessagesRequest;
import kz.fintech.models.whatsApp.SendRequestResponse;
import kz.fintech.models.whatsApp.ViewRequestResponse;
import org.springframework.stereotype.Service;

@Service
public interface WhatsAppService {
    SendRequestResponse sendMessages(SendMessagesRequest request);
    ViewRequestResponse viewMessages(String phoneNumber);
}
