package kz.fintech.apiservice.models.dtos;


import kz.fintech.models.auth.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;


/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

    private Long id;
    private String username;
    private UserRoleDto role;


}