package com.example.kullaniciAktiviteRaporuSistemi.services.concretes;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import com.example.kullaniciAktiviteRaporuSistemi.exceptions.DataNotFoundException;
import com.example.kullaniciAktiviteRaporuSistemi.messages.UserMessages;
import com.example.kullaniciAktiviteRaporuSistemi.modelmapper.ModelMapperService;
import com.example.kullaniciAktiviteRaporuSistemi.repository.ActivitesRepository;
import com.example.kullaniciAktiviteRaporuSistemi.repository.UserRepository;
import com.example.kullaniciAktiviteRaporuSistemi.result.DataResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.Result;
import com.example.kullaniciAktiviteRaporuSistemi.result.SuccessResult;
import com.example.kullaniciAktiviteRaporuSistemi.services.abstracts.UserService;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.DeleteUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.UpdateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetAllUserResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetByIdUserResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final ActivitesRepository activitesRepository;


    public DataResult<List<GetAllUserResponses>>  getAll() {
        List<Users> users = userRepository.findAll();
        List<GetAllUserResponses> getAllUserResponses = users.stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetAllUserResponses.class))
                .toList();

        return new DataResult<>(getAllUserResponses, true, UserMessages.USERS_LISTED);
    }


    public DataResult<GetByIdUserResponses> getById(int id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(UserMessages.DATA_NOT_FOUND));

        GetByIdUserResponses getByIdUserResponses = this.modelMapperService.forResponse()
                .map(user, GetByIdUserResponses.class);

        return new DataResult<>(getByIdUserResponses, true, UserMessages.USERS_LISTED);
    }

    @Override

    public Result update(UpdateUserRequests updateUserRequests) {

        Users user = this.modelMapperService.forRequest()
                .map(updateUserRequests, Users.class);

        this.userRepository.save(user);

        return new SuccessResult(UserMessages.USERS_UPDATED);
    }

    @Override
    public Result delete(DeleteUserRequests deleteUserRequests) {
        userRepository.deleteById(deleteUserRequests.getId());
        return new SuccessResult(UserMessages.USERS_DELETED);
    }

    public List<Activities> getUserActivitiesByDateRange(int userId, Date startDate, Date endDate) {
        try {
            return activitesRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();  // Hata ayıklama bilgilerini konsola yazdır
            throw e;
        }
    }



}


