package kz.fintech.apiservice.services.impl;

import kz.fintech.commons.feignclients.DbServiceClient;
import kz.fintech.models.auth.RefreshTokenDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements kz.fintech.apiservice.services.RefreshTokenService {
    private DbServiceClient dbServiceClient;


    @Override
    public RefreshTokenDto createRefreshToken(String username){
        RefreshTokenDto refreshToken = RefreshTokenDto.builder()
                .usersDto(dbServiceClient.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return dbServiceClient.saveToken(refreshToken);
    }
    @Override
    public Optional<RefreshTokenDto> findByToken(String token){
        return dbServiceClient.findByToken(token);
    }
    @Override
    public RefreshTokenDto verifyExpiration(RefreshTokenDto token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            dbServiceClient.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;

    }

}
