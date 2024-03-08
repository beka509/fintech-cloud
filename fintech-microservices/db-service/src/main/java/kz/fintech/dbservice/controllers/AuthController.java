package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.services.AuthService;
import kz.fintech.models.auth.RefreshTokenDto;
import kz.fintech.models.auth.UsersDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{id}")
    UsersDto findFirstById(@PathVariable("id") long id) {
        return authService.findFirstById(id);
    }

    @PostMapping("/save")
    UsersDto save(@RequestBody UsersDto usersDto) {
        return authService.save(usersDto);
    }


    @GetMapping("/username/{username}")
    UsersDto findByUsername(@PathVariable("username") String username) {
        return authService.findByUsername(username);
    }

    @GetMapping("/all")
    List<UsersDto> findAll() {
        return authService.findAll();
    }


    @PostMapping("/token/refresh")
    RefreshTokenDto refreshToken(@RequestBody UsersDto usersDto) {
        return authService.refreshToken(usersDto);
    }

    @PostMapping("/token/save")
    RefreshTokenDto saveToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.saveToken(refreshTokenDto);
    }

    @DeleteMapping("/token")
    void delete(@RequestBody RefreshTokenDto refreshTokenDto) {
        authService.deleteToken(refreshTokenDto);
    }


    @GetMapping("/token/{token}")
    Optional<RefreshTokenDto> findByToken(@PathVariable("token") String token) {
        return authService.findByToken(token);
    }

    @PostMapping("/verifyExpiration")
    RefreshTokenDto verifyExpiration(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.verifyExpiration(refreshTokenDto);
    }

}
