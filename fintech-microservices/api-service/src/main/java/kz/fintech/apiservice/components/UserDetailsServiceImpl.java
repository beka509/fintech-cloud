package kz.fintech.apiservice.components;

import kz.fintech.commons.feignclients.DbServiceClient;
import kz.fintech.models.auth.UsersDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import kz.fintech.apiservice.utils.CustomUserDetails;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final DbServiceClient dbServiceClient;

    public UserDetailsServiceImpl(DbServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Entering in loadUserByUsername Method...");
        UsersDto user = null;

        try {
            user = dbServiceClient.findByUsername(username);

        } catch (Exception e) {
            log.error("Error::: ", e);
        }
        if (user == null) {
            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        log.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }
}
