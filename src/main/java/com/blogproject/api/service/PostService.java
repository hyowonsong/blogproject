package com.blogproject.api.service;

import com.blogproject.api.domain.Post;
import com.blogproject.api.repository.PostRepository;
import com.blogproject.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    // 생성자 주입 -> RequiredArgsConstructor
    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        // postCreate 를 Entity 형태로 바꿔줘야
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);
    }
}
