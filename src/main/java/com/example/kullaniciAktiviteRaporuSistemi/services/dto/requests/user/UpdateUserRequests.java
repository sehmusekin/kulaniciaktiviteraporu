package com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequests {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String gsm;

    private String username;

    private String password;


}

