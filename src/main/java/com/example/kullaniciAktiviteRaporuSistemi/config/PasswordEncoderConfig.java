package com.example.kullaniciAktiviteRaporuSistemi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for creating a BCryptPasswordEncoder bean in the Rent a Car system.
 * BCryptPasswordEncoder is used for password encoding.
 *
 * @author [Harun Yılmaz]
 * @see BCryptPasswordEncoder
 */

@Configuration
public class    PasswordEncoderConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
