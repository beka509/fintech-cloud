package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Privilege implements Serializable {
    private static final long serialVersionUID = -4180001600643490801L;

    private int privilegeId;
    private String privilegeName;
    private String privilegeCode;
    private Menu menu;
}
