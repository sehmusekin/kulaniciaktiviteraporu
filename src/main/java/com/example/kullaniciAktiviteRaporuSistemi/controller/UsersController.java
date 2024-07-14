package com.example.kullaniciAktiviteRaporuSistemi.controller;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Activities;
import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import com.example.kullaniciAktiviteRaporuSistemi.repository.UserRepository;
import com.example.kullaniciAktiviteRaporuSistemi.result.DataResult;
import com.example.kullaniciAktiviteRaporuSistemi.result.Result;
import com.example.kullaniciAktiviteRaporuSistemi.services.abstracts.UserService;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.CreateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.DeleteUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.UpdateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetAllUserResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetByIdUserResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.33:3000"})

@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/getById")
    public DataResult<GetByIdUserResponses> getUserById(@RequestParam int id) {
        return userService.getById(id);
    }


    @GetMapping("/getAll")
    public DataResult<List<GetAllUserResponses>> getAllUser() {
        return userService.getAll();
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody UpdateUserRequests updateUserRequests) {
        return userService.update(updateUserRequests);
    }




    @DeleteMapping("/delete")
    public Result deleteUser(@RequestBody DeleteUserRequests deleteUserRequests) {
        return userService.delete(deleteUserRequests);
    }

    @GetMapping("/user/{userId}/activities")
    public ResponseEntity<List<Activities>> getUserActivities(
            @PathVariable int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        try {
            List<Activities> activities = userService.getUserActivitiesByDateRange(userId, startDate, endDate);
            return ResponseEntity.ok(activities);
        } catch (Exception e) {
            e.printStackTrace();  // Hata ayıklama bilgilerini konsola yazdır
            return ResponseEntity.status(500).body(null);
        }
    }
}
