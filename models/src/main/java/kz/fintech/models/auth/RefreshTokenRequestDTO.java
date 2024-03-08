package kz.fintech.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDTO {
    private String token;
}