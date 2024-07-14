package com.example.kullaniciAktiviteRaporuSistemi.services.JWT;

import com.example.kullaniciAktiviteRaporuSistemi.entities.Users;
import com.example.kullaniciAktiviteRaporuSistemi.modelmapper.ModelMapperService;
import com.example.kullaniciAktiviteRaporuSistemi.repository.UserRepository;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.requests.user.CreateUserRequests;
import com.example.kullaniciAktiviteRaporuSistemi.services.dto.responses.user.GetByIdUserResponses;
import com.example.kullaniciAktiviteRaporuSistemi.services.rules.asdf.UserBusinessRules;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRules userBusinessRules;

    public Optional<GetByIdUserResponses> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> modelMapperService.forResponse().map(user, GetByIdUserResponses.class));
    }

    public Users register(CreateUserRequests createUserRequest) {
        userBusinessRules.checkIfUserExists(createUserRequest.email(), createUserRequest.gsm());

        Users user = Users.builder()
                .firstName(createUserRequest.firstName())
                .lastName(createUserRequest.lastName())
                .email(createUserRequest.email())
                .gsm(createUserRequest.gsm())
                .username(createUserRequest.username())
                .password(bCryptPasswordEncoder.encode(createUserRequest.password()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);

    }



}
