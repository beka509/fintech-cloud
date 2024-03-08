package kz.fintech.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.fintech.models.whatsApp.SendMessagesRequest;
import kz.fintech.models.whatsApp.SendRequestResponse;
import kz.fintech.models.whatsApp.ViewRequestResponse;
import kz.fintech.props.WhatsAppProps;
import kz.fintech.services.WhatsAppService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Service
@Slf4j
public class WhatsAppServiceImpl implements WhatsAppService {
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final WhatsAppProps whatsAppProps;

    public WhatsAppServiceImpl(ObjectMapper objectMapper,
                               WhatsAppProps whatsAppProps) {
        this.objectMapper = objectMapper;
        this.whatsAppProps = whatsAppProps;
        this.restTemplate = new RestTemplate();
    }

    @SneakyThrows
    public SendRequestResponse sendMessages(SendMessagesRequest request) {
        SendRequestResponse result = new SendRequestResponse();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            headers.set("Authorization", whatsAppProps.getAuth());
            val json = objectMapper.writeValueAsString(request);

            log.info("text => " + request.getText());
            log.info("json => " + json);

            ResponseEntity<SendRequestResponse> response = restTemplate.exchange(
                whatsAppProps.getUrl() + whatsAppProps.getChatAppLine() +
                "/messengers/grWhatsApp/chats/" + request.getPhoneNumber() + "/messages/text",
                HttpMethod.POST,
                new HttpEntity<>(json, headers), SendRequestResponse.class);

            result = objectMapper.convertValue(response.getBody(), SendRequestResponse.class);
        } catch (Exception ex) {
            log.error("Error, sendMessages => {}", ex.getMessage());
            throw new RuntimeException("Error, sendMessages => " + ex.getMessage());
        }
        return result;
    }

    @SneakyThrows
    public ViewRequestResponse viewMessages(@PathVariable("phoneNumber") String phoneNumber) {
        ViewRequestResponse result = new ViewRequestResponse();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", whatsAppProps.getAuth());

            ResponseEntity<ViewRequestResponse> response = restTemplate.exchange(
                    whatsAppProps.getUrl() + whatsAppProps.getChatAppLine() +
                            "/messengers/grWhatsApp/chats/" + phoneNumber + "@c.us/messages",
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers), ViewRequestResponse.class);

            result = objectMapper.convertValue(response.getBody(), ViewRequestResponse.class);
        } catch (Exception ex) {
            log.error("Error, sendMessages => {}", ex.getMessage());
            throw new RuntimeException("Error, sendMessages => " + ex.getMessage());
        }
        return result;
    }
}
