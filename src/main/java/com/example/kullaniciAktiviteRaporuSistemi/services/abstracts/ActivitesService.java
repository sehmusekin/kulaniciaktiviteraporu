package com.example.kullaniciAktiviteRaporuSistemi.services.abstracts;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.result.DataResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.Result;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.CreateActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.DeleteActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.UpdateActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.CreateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.activites.GetAllActivitesResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.activites.GetByIdActivitesResponses;

import java.util.List;

public interface ActivitesService {

    DataResult<List<GetAllActivitesResponses>> getAll();

    DataResult<GetByIdActivitesResponses> getByID(int id);

    Result add(CreateActivitiesRequests createActivitiesRequests);

    Result update(UpdateActivitiesRequests updateActivitiesRequests);

    Result delete(DeleteActivitiesRequests deleteActivitiesRequests);



}

