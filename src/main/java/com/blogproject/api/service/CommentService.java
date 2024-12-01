package com.blogproject.api.service;


import com.blogproject.api.domain.Comment;
import com.blogproject.api.domain.Post;
import com.blogproject.api.repository.post.PostRepository;
import com.blogproject.api.repository.comment.CommentRepository;
import com.blogproject.api.request.comment.CommentCreate;
import com.blogproject.api.request.comment.CommentDelete;
import lombok.RequiredArgsConstructor;
import com.blogproject.api.exception.InvalidPassword;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogproject.api.exception.PostNotFound;
import com.blogproject.api.exception.CommentNotFound;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void write(Long postId, CommentCreate request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        Comment comment = Comment.builder()
                .author(request.getAuthor())
                .password(encryptedPassword)
                .content(request.getContent())
                .build();

        post.addComment(comment);
    }

    public void delete(Long commentId, CommentDelete request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);

        String encryptedPassword = comment.getPassword();
        if (!passwordEncoder.matches(request.getPassword(), encryptedPassword)) {
            throw new InvalidPassword();
        }

        commentRepository.delete(comment);
    }
}
