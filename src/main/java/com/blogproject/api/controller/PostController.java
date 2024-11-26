package com.blogproject.api.controller;


import com.blogproject.api.config.data.UserSession;
import com.blogproject.api.request.PostCreate;
import com.blogproject.api.request.PostEdit;
import com.blogproject.api.response.PostResponse;
import com.blogproject.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Interceptor 를 위한 테스트
    @GetMapping("/foo")
    public Long foo(UserSession userSession){
        log.info(">>>{}", userSession.id);
        return userSession.id;
    }

    @GetMapping("/bar")
    public String bar(UserSession userSession) {
        return "인증이 필요한 페이지";
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request){
        request.validate();
        postService.write(request);
    }

    /**
     * /posts - > 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId){
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable){
        return postService.getList(pageable);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request){
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }
}
