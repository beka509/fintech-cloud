package kz.fintech.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {
    private long id;
    private String username;
    private String fullName;
    private String password;
    private long roleId;
}
