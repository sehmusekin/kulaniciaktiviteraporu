package com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Builder
public class CreateActivitiesRequests {
    private String username;
    private String activityType;
    private Date date;
    private String explanation;

}
