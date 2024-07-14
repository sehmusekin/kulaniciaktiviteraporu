package com.example.kullaniciAktiviteRaporuSistemi.controller;


import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import com.example.kullaniciAktiviteRaporuSistemi.services.JWT.AuthService;
import com.example.kullaniciAktiviteRaporuSistemi.services.JWT.JwtService;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.CreateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
@CrossOrigin
public class AuthsController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Users register(@RequestBody CreateUserRequests createUserRequest) {
        return this.authService.register(createUserRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginRequest.username());
        }
        log.info("invalid username " + loginRequest.username());
        throw new UsernameNotFoundException("invalid username {} " + loginRequest.username());
    }
}
