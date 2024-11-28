package com.blogproject.api.controller;


import com.blogproject.api.config.AppConfig;
import com.blogproject.api.domain.Signup;
import com.blogproject.api.request.Login;
import com.blogproject.api.response.SessionResponse;
import com.blogproject.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    // private static final String KEY = "hR7Go0KFDhBk5jl0J2ht0Q6B7LtK/TKhiU3GAlHte2c=";
    private final AuthService authService;
    private final AppConfig appConfig;

    // 3. jwt 로 처리
    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        Long userId  = authService.signin(login);

        SecretKey key = Keys.hmacShaKeyFor(appConfig.getJwtKey());

        // hR7Go0KFDhBk5jl0J2ht0Q6B7LtK/TKhiU3GAlHte2c=
        String jws = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(key)
                .setIssuedAt(new Date())
                .compact();

        log.info("Generated JWT Token: {}", jws); // JWT 토큰 확인
        return new SessionResponse(jws);
    }


    // 2. 쿠키로 처리
//    @PostMapping("/auth/login")
//    public ResponseEntity<Object> login(@RequestBody Login login) {
//        String accessToken = authService.signin(login);
//        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
//                .domain("localhost")
//                .path("/")
//                .httpOnly(true)
//                .secure(false)
//                .maxAge(Duration.ofDays(30))
//                .sameSite("Strict")
//                .build();
//
//        log.info(">>>>>>>>> cookie = {}", cookie.toString());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .build();
//    }

    // 1. 클라이언트에서 직접 저장
//    @PostMapping("/auth/login")
//    public SessionResponse login(@RequestBody Login login) {
//        String accessToken = authService.signin(login);
//        return new SessionResponse(accessToken);
//    }

    // 회원 가입
    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }
}
