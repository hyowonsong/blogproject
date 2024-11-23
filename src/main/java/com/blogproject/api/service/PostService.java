package com.blogproject.api.service;

import com.blogproject.api.domain.Post;
import com.blogproject.api.exception.PostNotFound;
import com.blogproject.api.repository.PostRepository;
import com.blogproject.api.request.PostCreate;
import com.blogproject.api.request.PostEdit;
import com.blogproject.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponse get(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(Pageable pageable) {
        return postRepository.findAll(pageable).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        post.edit(
                postEdit.getTitle() != null ? postEdit.getTitle() : post.getTitle(),
                postEdit.getContent() != null ? postEdit.getContent() : post.getContent());
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}
