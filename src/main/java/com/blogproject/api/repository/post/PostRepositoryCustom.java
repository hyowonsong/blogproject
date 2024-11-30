package com.blogproject.api.repository.post;


import com.blogproject.api.domain.Post;
import com.blogproject.api.request.post.PostSearch;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {

    Page<Post> getList(PostSearch postSearch);
}
