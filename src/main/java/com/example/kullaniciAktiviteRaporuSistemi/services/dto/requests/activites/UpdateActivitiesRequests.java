package com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateActivitiesRequests {
    private int id;

    private Users user;

    private String activityType;

    private Date date;

    private String explanation;
}
