package com.example.kullaniciAktiviteRaporuSistemi.services.rules.asdf;

import com.example.kullaniciAktiviteRaporuSistemi.messages.UserMessages;
import com.example.kullaniciAktiviteRaporuSistemi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void checkIfUserExists(String email, String gsm) {
        if (this.userRepository.existsByEmail(email)) {
            throw new RuntimeException(UserMessages.USER_ALREADY_EXISTS);
        }
        if (this.userRepository.existsByGsm(gsm)) {
            throw new RuntimeException(UserMessages.USER_ALREADY_EXISTS);
        }
    }
}
