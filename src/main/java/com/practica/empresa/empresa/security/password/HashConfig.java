package com.practica.empresa.empresa.security.password;

import com.practica.empresa.empresa.security.password.impl.BCryptStrategy;
import com.practica.empresa.empresa.security.password.impl.ShaStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HashConfig {

    @Bean
    @Primary
    public HashStrategy bCryptStrategy() {
        return new BCryptStrategy();
    }

    @Bean
    public HashStrategy shaStrategy() {
        return new ShaStrategy();
    }
}
