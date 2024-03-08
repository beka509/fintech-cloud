package kz.fintech.dbservice.services.impl;

import kz.fintech.dbservice.entities.RefreshTokenEntity;
import kz.fintech.dbservice.entities.RoleEntity;
import kz.fintech.dbservice.entities.UsersEntity;
import kz.fintech.dbservice.repos.TokenRepository;
import kz.fintech.dbservice.repos.UsersRepository;
import kz.fintech.dbservice.services.AuthService;
import kz.fintech.models.auth.RefreshTokenDto;
import kz.fintech.models.auth.UsersDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public UsersDto findFirstById(long id) {
        return modelMapper.map(usersRepository.findById(id), UsersDto.class);
    }

    @Override
    public UsersDto save(UsersDto user) {

        UsersEntity usersEntity = new UsersEntity();
        if (user.getId() > 0)
            usersEntity.setId(user.getId());
        usersEntity.setUsername(user.getUsername());
        usersEntity.setPassword(user.getPassword());
        usersEntity.setFullName(user.getFullName());
        usersEntity.setRole(new RoleEntity(user.getRoleId()));
        return modelMapper.map(usersRepository.save(usersEntity), UsersDto.class);
    }

    @Override
    public RefreshTokenDto refreshToken(UsersDto usersDto) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .userInfo(usersRepository.findByUsername(usersDto.getUsername()))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return modelMapper.map(tokenRepository.save(refreshToken), RefreshTokenDto.class);
    }

    @Override
    public UsersDto findByUsername(String username) {
        return modelMapper.map(usersRepository.findByUsername(username), UsersDto.class);
    }

    @Override
    public List<UsersDto> findAll() {
        return modelMapper.map(usersRepository.findAll(), new TypeToken<List<UsersDto>>() {
        }.getType());
    }

    @Override
    public RefreshTokenDto saveToken(RefreshTokenDto refreshTokenDto) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .userInfo(usersRepository.findByUsername(refreshTokenDto.getUsername()))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return modelMapper.map(tokenRepository.save(refreshToken), RefreshTokenDto.class);
    }

    @Override
    public void deleteToken(RefreshTokenDto refreshTokenDto) {
        tokenRepository.deleteByToken(refreshTokenDto.getToken());
    }

    @Override
    public RefreshTokenDto verifyExpiration(RefreshTokenDto refreshTokenDto) {
        if (refreshTokenDto.getExpiryDate().compareTo(Instant.now()) < 0) {
            tokenRepository.deleteByToken(refreshTokenDto.getToken());
            throw new RuntimeException(refreshTokenDto.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return refreshTokenDto;
    }

    @Override
    public Optional<RefreshTokenDto> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
