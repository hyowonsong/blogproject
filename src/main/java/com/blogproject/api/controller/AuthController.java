package com.blogproject.api.controller;


import com.blogproject.api.config.AppConfig;
import com.blogproject.api.request.Signup;
import com.blogproject.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;

//    // api 테스트 할 때 필요했던 메서드
//    @GetMapping("/api/auth/login")
//    public String login() {
//        return "로그인 페이지입니다.";
//    }

    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }

}
