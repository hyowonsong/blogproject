package com.blogproject.api.service;

import com.blogproject.api.crypto.PasswordEncoder;
import com.blogproject.api.domain.Session;
import com.blogproject.api.domain.Signup;
import com.blogproject.api.domain.User;
import com.blogproject.api.exception.AlreadyExistsEmailException;
import com.blogproject.api.exception.InvalidSigninInformation;
import com.blogproject.api.repository.UserRepository;
import com.blogproject.api.request.Login;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public Long signin(Login login) {
        // 이메일로 매칭되는 사용자를 가져온 다음 사용자의 암호화를 평문과 비교해서 매칭
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(InvalidSigninInformation::new);

        com.blogproject.api.crypto.PasswordEncoder encoder = new com.blogproject.api.crypto.PasswordEncoder();
        var matches = encoder.matches(login.getPassword(), user.getPassword());
        if (!matches) {
            throw new InvalidSigninInformation();
        }

        return user.getId();
    }


    // 현재 평문으로 처리되어 있기 때문에 암호화한 비밀번호는 이걸로 해결되지 않음 -> 암호화 해결 코드로 만들어야.
//    @Transactional
//    public Long signin(Login login){
//        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
//                .orElseThrow(InvalidSigninInformation::new);
//
//        Session session = user.addSession();
//
//        return user.getId();
//    }

    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findByEmail(signup.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        PasswordEncoder encoder = new PasswordEncoder();
        String encryptedPassword = encoder.encrpyt(signup.getPassword());

        var user = User.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .name(signup.getName())
                .build();
        userRepository.save(user);
    }


}
