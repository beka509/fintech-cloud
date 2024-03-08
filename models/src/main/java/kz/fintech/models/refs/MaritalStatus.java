package kz.fintech.models.refs;

import lombok.Data;

@Data
public class MaritalStatus {
    private String code;
    private String name;
    private String gender;
    private int sortOrder;
}