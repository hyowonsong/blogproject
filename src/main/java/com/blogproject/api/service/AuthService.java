package com.blogproject.api.service;

import com.blogproject.api.domain.Session;
import com.blogproject.api.domain.User;
import com.blogproject.api.exception.InvalidSigninInformation;
import com.blogproject.api.repository.UserRepository;
import com.blogproject.api.request.Login;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public String signin(Login login){
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        Session session = user.addSession();

        return session.getAccessToken();
    }
}
