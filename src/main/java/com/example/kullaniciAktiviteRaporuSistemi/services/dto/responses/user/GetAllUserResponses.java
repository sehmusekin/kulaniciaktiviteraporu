package com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponses {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gsm;

}
