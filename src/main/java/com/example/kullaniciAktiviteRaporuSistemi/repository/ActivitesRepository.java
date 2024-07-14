package com.example.kullaniciAktiviteRaporuSistemi.repository;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ActivitesRepository extends JpaRepository<Activities, Integer> {

    List<Activities> findByUserIdAndDateBetween(int userId, Date startDate, Date endDate);

   


}
