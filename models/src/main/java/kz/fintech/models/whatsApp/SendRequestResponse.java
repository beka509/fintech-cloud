package kz.fintech.models.whatsApp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendRequestResponse {
    private Boolean success;
    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Data{
        private String id;
        private String chatId;
    }
}
