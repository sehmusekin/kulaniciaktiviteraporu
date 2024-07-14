package com.example.kullaniciAktiviteRaporuSistemi.controller;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.repository.ActivitesRepository;
import com.example.kullaniciAktiviteRaporuSistemi.result.DataResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.ErrorResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.Result;
import com.example.kullaniciAktiviteRaporuSistemi.services.abstracts.ActivitesService;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.CreateActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.DeleteActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.activites.UpdateActivitiesRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.activites.GetAllActivitesResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.activites.GetByIdActivitesResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/activites")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ActivitesController {
    private final ActivitesService activitesService;
    private final ActivitesRepository activitesRepository;

    @GetMapping("/getById")
    public DataResult<GetByIdActivitesResponses> getActivitesById(@RequestParam int id){
        return activitesService.getByID(id);
    }


    @GetMapping("/getAll")
    public DataResult<List<GetAllActivitesResponses>> getAllActivites(){
        return activitesService.getAll();
    }

    @PutMapping("/update")
    public Result updateActivites(@RequestBody UpdateActivitiesRequests updateActivitiesRequests){
        return activitesService.update(updateActivitiesRequests);

    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateActivitiesRequests createActivitiesRequests) {
        return activitesService.add(createActivitiesRequests);
    }
    @DeleteMapping("/delete")
    public Result deleteActivities( @RequestBody DeleteActivitiesRequests deleteActivitiesRequests) {
        return activitesService.delete(deleteActivitiesRequests);
    }
}
