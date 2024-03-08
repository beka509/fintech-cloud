package kz.fintech.apiservice.services;

import kz.fintech.apiservice.models.dtos.UserResponse;
import kz.fintech.models.auth.UsersDto;

import java.util.List;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */
public interface UserService {
    UsersDto saveUser(UsersDto userRequest);

    UserResponse getUser();

    List<UsersDto> getAllUser();

}