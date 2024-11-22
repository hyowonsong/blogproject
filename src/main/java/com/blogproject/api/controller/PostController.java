package com.blogproject.api.controller;

import com.blogproject.api.request.PostCreate;
import com.blogproject.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request){
        postService.write(request);

//        // 데이터 검증
//        String title = params.getTitle();
//        if (title == null || title.equals("")){
//            //error
//            throw new Exception("타이틀값이 없어요!");
//        }
//
//        String content = params.getContent();
//        if (content == null || content.equals("")){
//            // error
//        }
    }
}
