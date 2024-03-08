package kz.fintech.apiservice.controllers;

import io.swagger.annotations.Api;
import kz.fintech.apiservice.components.JwtService;
import kz.fintech.apiservice.services.RefreshTokenService;
import kz.fintech.apiservice.services.UserService;
import kz.fintech.commons.feignclients.DbServiceClient;
import kz.fintech.models.auth.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@Api(value = "api", tags = "API для aвторизаций и управление ролями")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private DbServiceClient dbServiceClient;
    private UserService userService;
    private RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody UserDTO userDTO) {
        log.info("Req {}", userDTO);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        RefreshTokenDto refreshToken = refreshTokenService.createRefreshToken(userDTO.getUsername());

        if (authentication.isAuthenticated()) {
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(userDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return dbServiceClient.findByToken(refreshTokenRequestDTO.getToken())
                .map(dbServiceClient::verifyExpiration)
                .map(RefreshTokenDto::getUsersDto)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @PostMapping(value = "/save")
    public UsersDto saveUser(@RequestBody UsersDto usersDto) {
        return userService.saveUser(usersDto);
    }

    @GetMapping("/users")
    public List<UsersDto> getAllUsers() {
        return dbServiceClient.findAll().stream().peek(usersDto -> usersDto.setPassword(null)).toList();
    }
}
