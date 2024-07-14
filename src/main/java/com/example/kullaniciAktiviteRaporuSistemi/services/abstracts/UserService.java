package com.example.kullaniciAktiviteRaporuSistemi.services.abstracts;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.result.DataResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.Result;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.DeleteUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.UpdateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetAllUserResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetByIdUserResponses;


import java.util.Date;
import java.util.List;


public interface UserService {
    DataResult<List<GetAllUserResponses>> getAll();

    DataResult<GetByIdUserResponses> getById(int id);


    Result update(UpdateUserRequests updateUserRequests);

    Result delete(DeleteUserRequests deleteUserRequests);


    List<Activities> getUserActivitiesByDateRange(int userId, Date startDate, Date endDate);
}