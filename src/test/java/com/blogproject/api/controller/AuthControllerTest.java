package com.blogproject.api.controller;

import com.blogproject.api.domain.User;

import com.blogproject.api.repository.UserRepository;

import com.blogproject.api.request.Signup;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

//    @Test
//    @DisplayName("로그인 성공")
//    void test1() throws Exception {
//        // given
//        userRepository.save(User.builder()
//                .name("맨")
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build());
//
//        Login login = Login.builder()
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(login);
//
//        // expected
//        mockMvc.perform(post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.code").value("400"))
////                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
////                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력하세요."))
//                .andDo(print());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("로그인 성공후 세션 1개 생성")
//    void test2() throws Exception {
//        // given
//        User user = userRepository.save(User.builder()
//                .name("맨")
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build());
//
//        Login login = Login.builder()
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(login);
//
//        // expected
//        mockMvc.perform(post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        assertEquals(1L, user.getSessions().size());
//    }
//
//    @Test
//    @DisplayName("로그인 성공후 세션 응답")
//    void test3() throws Exception {
//        // given
//        User user = userRepository.save(User.builder()
//                .name("맨")
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build());
//
//        Login login = Login.builder()
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(login);
//
//        // expected
//        mockMvc.perform(post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.accessToken", notNullValue()))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 후 권한이 필요한 페이지 접속한다 /foo")
//    void test4() throws Exception {
//        // given
//        User user = User.builder()
//                .name("맨")
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build();
//        Session session = user.addSession();
//        userRepository.save(user);
//
//        // expected
//        mockMvc.perform(get("/foo")
//                        .header("Authorization", session.getAccessToken())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지에 접속할 수 없다.")
//    void test5() throws Exception {
//        // given
//        User user = User.builder()
//                .name("맨")
//                .email("woniwoni@gmail.com")
//                .password("1234")
//                .build();
//        Session session = user.addSession();
//        userRepository.save(user);
//
//        // expected
//        mockMvc.perform(get("/foo")
//                        .header("Authorization", session.getAccessToken() + "-other")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andDo(print());
//    }

    @Test
    @DisplayName("회원가입")
    void test6() throws Exception {
        // given
        Signup signup = Signup.builder()
                .email("woniwoni@gmail.com")
                .password("1234")
                .name("맨")
                .build();

        // expected
        mockMvc.perform(post("/api/auth/signup")
                        .content(objectMapper.writeValueAsString(signup))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
