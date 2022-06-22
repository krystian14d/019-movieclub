package pl.javastart.movieclub.config.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSecurityConfig {

    private static final String USER_ROLE = "USER";
    private static final String EDITOR_ROLE = "EDITOR";
    private static final String ADMIN_ROLE = "ADMIN";

}
