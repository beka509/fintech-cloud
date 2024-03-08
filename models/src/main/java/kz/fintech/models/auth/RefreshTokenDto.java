package kz.fintech.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RefreshTokenDto {

    private int id;
    private String username;
    private String token;
    private Instant expiryDate;
    private UsersDto usersDto;
}
