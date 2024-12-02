package com.blogproject.api.service;

import com.blogproject.api.domain.User;
import com.blogproject.api.exception.UserNotFound;
import com.blogproject.api.repository.UserRepository;
import com.blogproject.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        return new UserResponse(user);
    }
}
