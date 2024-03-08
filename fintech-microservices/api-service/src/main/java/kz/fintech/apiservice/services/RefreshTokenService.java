package kz.fintech.apiservice.services;

import kz.fintech.models.auth.RefreshTokenDto;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshTokenDto createRefreshToken(String username);

    Optional<RefreshTokenDto> findByToken(String token);

    RefreshTokenDto verifyExpiration(RefreshTokenDto token);
}
