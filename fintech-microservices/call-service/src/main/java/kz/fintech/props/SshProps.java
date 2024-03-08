package kz.fintech.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
@ConfigurationProperties(prefix = "ssh")
public class SshProps {
    private String url;
    private String host;
    private int port;
    private String user;
    private String password;
}
