package kz.fintech.apiservice.services.impl;

import kz.fintech.apiservice.models.dtos.UserResponse;
import kz.fintech.apiservice.services.UserService;
import kz.fintech.commons.feignclients.DbServiceClient;
import kz.fintech.models.auth.UsersDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private DbServiceClient dbServiceClient;
    private final ModelMapper modelMapper;


    @Override
    public UsersDto saveUser(UsersDto userRequest) {
        if (userRequest.getUsername() == null) {
            throw new RuntimeException("Parameter username is not found in request..!!");
        } else if (userRequest.getPassword() == null) {
            throw new RuntimeException("Parameter password is not found in request..!!");
        }

        UsersDto savedUser;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        UsersDto user = modelMapper.map(userRequest, UsersDto.class);
        user.setPassword(encodedPassword);
        if (userRequest.getId() > 0) {
            UsersDto oldUser = dbServiceClient.findFirstById(userRequest.getId());
            if (oldUser != null) {
                oldUser.setId(user.getId());
                oldUser.setPassword(user.getPassword());
                oldUser.setUsername(user.getUsername());
                oldUser.setRoleId(user.getRoleId());
                savedUser = dbServiceClient.save(oldUser);
                dbServiceClient.refreshToken(savedUser);
            } else {
                throw new RuntimeException("Can't find record with identifier: " + userRequest.getId());
            }
        } else {
            savedUser = dbServiceClient.save(user);
        }
        dbServiceClient.refreshToken(savedUser);
        savedUser.setPassword(null);
        return savedUser;
    }

    @Override
    public UserResponse getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();
        UsersDto user = dbServiceClient.findByUsername(usernameFromAccessToken);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UsersDto> getAllUser() {
        List<UsersDto> users = dbServiceClient.findAll();
        Type setOfDTOsType = new TypeToken<List<UserResponse>>() {
        }.getType();
        return modelMapper.map(users, setOfDTOsType);
    }


}