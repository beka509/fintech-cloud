package kz.fintech.dbservice.services;

import kz.fintech.models.auth.RefreshTokenDto;
import kz.fintech.models.auth.UsersDto;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    UsersDto findFirstById(long id);

    UsersDto save(UsersDto request);

    RefreshTokenDto refreshToken(UsersDto usersDto);
    UsersDto findByUsername(String username);
    List<UsersDto> findAll();


    RefreshTokenDto saveToken(RefreshTokenDto refreshTokenDto);

    void deleteToken(RefreshTokenDto refreshTokenDto);

    RefreshTokenDto verifyExpiration(RefreshTokenDto refreshTokenDto);

    Optional<RefreshTokenDto> findByToken(String token);
}
