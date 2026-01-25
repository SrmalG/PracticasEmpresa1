package com.practica.empresa.empresa.security;

import com.practica.empresa.empresa.security.impl.BCryptStrategy;
import com.practica.empresa.empresa.security.impl.ShaStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HashConfig {

    @Bean
    @Primary
    public HashStrategy shaStrategy() {
        return new ShaStrategy();
    }

    @Bean
    public HashStrategy bCryptStrategy() {
        return new BCryptStrategy();
    }
}
