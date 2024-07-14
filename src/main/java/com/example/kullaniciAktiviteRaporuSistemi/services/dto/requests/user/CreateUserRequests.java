package com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Builder


public record CreateUserRequests (
        String username,
        String firstName,

        String lastName,

        String email,

        String gsm,

        String password



)  {





}
