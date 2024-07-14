package com.example.kullaniciAktiviteRaporuSistemi.services.concretes;


import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import com.example.kullaniciAktiviteRaporuSistemi.exceptions.DataNotFoundException;
import com.example.kullaniciAktiviteRaporuSistemi.messages.ActivitesMessages;
import com.example.kullaniciAktiviteRaporuSistemi.modelmapper.ModelMapperService;
import com.example.kullaniciAktiviteRaporuSistemi.repository.ActivitesRepository;
import com.example.kullaniciAktiviteRaporuSistemi.repository.UserRepository;
import com.example.kullaniciAktiviteRaporuSistemi.result.DataResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.ErrorResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.Result;
import com.example.kullaniciAktiviteRaporuSistemi.result.SuccessResult;
import com.example.kullaniciAktiviteRaporuSistemi.services.abstracts.ActivitesService;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.CreateActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.DeleteActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.UpdateActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.activites.GetAllActivitesResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.activites.GetByIdActivitesResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivitiesManager implements ActivitesService {

    private final ActivitesRepository activitesRepository;
    private final ModelMapperService modelMapperService;
    private final UserRepository userRepository;


    @Override
    public DataResult<List<GetAllActivitesResponses>> getAll() {
        List<Activities> activities = activitesRepository.findAll();
        List<GetAllActivitesResponses> getAllActivitesResponses = activities.stream()
                .map(activitie -> this.modelMapperService.forResponse()
                        .map(activitie, GetAllActivitesResponses.class))
                .toList();
        return new DataResult<>(getAllActivitesResponses, true, ActivitesMessages.ACTIVITES_LISTED);
    }

    @Override
    public DataResult<GetByIdActivitesResponses> getByID(int id) {
        Activities activities = activitesRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ActivitesMessages.DATA_NOT_FOUND));
        GetByIdActivitesResponses getByIdActivitesResponses = this.modelMapperService.forResponse()
                .map(activities, GetByIdActivitesResponses.class);
        return new DataResult<>(getByIdActivitesResponses, true, ActivitesMessages.ACTIVITES_LISTED);
    }

    @Override
    public Result add(CreateActivitiesRequests createActivitiesRequests) {
        // username ile Users nesnesini bulun
        Users user = userRepository.findByUsername(createActivitiesRequests.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));

        Activities activities = this.modelMapperService.forRequest()
                .map(createActivitiesRequests, Activities.class);

        // Users nesnesini Activities nesnesine atayın
        activities.setUser(user);

        this.activitesRepository.save(activities);
        return new SuccessResult(ActivitesMessages.ACTIVITES_ADDED);
    }

    @Override
    public Result update(UpdateActivitiesRequests updateActivitiesRequests) {
        Activities activities = this.modelMapperService.forRequest()
                .map(updateActivitiesRequests, Activities.class);
        this.activitesRepository.save(activities);
        return new SuccessResult(ActivitesMessages.ACTIVITES_UPDATED);
    }

    @Override
    public Result delete(DeleteActivitiesRequests deleteActivitiesRequests) {
        activitesRepository.deleteById(deleteActivitiesRequests.getId());
        return new SuccessResult(ActivitesMessages.ACTIVITES_DELETED);
    }








}
