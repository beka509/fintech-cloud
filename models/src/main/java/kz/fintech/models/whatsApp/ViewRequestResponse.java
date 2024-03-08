package kz.fintech.models.whatsApp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewRequestResponse {
    private Boolean success;
    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Data{
        private List<Items> items;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Items{
        private String id;
        private Boolean fromApi;
        private Boolean fromMe;
        private String side;
        private Long time;
        private String status;
        private String error;
        private String type;
        private String subtype;
        private Message message;
        private String quotedMessage;
        private FromUser fromUser;
        private Created created;
        private String reactions;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Message{
        private String text;
        private String caption;
        private String file;
        private String location;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FromUser{
        private String id;
        private String username;
        private String name;
        private String phone;
    }

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Created{
        private Integer id;
    }
}
