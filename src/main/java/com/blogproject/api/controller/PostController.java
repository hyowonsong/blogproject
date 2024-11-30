package com.blogproject.api.controller;


import com.blogproject.api.config.UserPrincipal;
import com.blogproject.api.request.post.PostCreate;
import com.blogproject.api.request.post.PostEdit;
import com.blogproject.api.request.post.PostSearch;
import com.blogproject.api.response.PagingResponse;
import com.blogproject.api.response.PostResponse;
import com.blogproject.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

//    // Interceptor 를 위한 테스트
//    @GetMapping("/foo")
//    public Long foo(UserSession userSession){
//        log.info(">>>{}", userSession.id);
//        return userSession.id;
//    }
//
//    @GetMapping("/bar")
//    public String bar(UserSession userSession) {
//        return "인증이 필요한 페이지";
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/posts")
    public void post(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid PostCreate request) {
        postService.write(userPrincipal.getUserId(), request);
    }

    /**
     * /posts - > 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */


    @GetMapping("posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("posts")
    public PagingResponse<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') && hasPermission(#postId, 'POST', 'DELETE')")
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
