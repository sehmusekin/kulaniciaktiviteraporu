package com.example.kullaniciAktiviteRaporuSistemi.repository;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByGsm(String gsm);



}
