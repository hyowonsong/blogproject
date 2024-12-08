package com.blogproject.api.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = BlogMockSecurityContext.class)
public @interface BlogMockUser {

    String name() default "맨";

    String email() default "woniwoni@gmail.com";

    String password() default "";

//    String role() default "ROLE_ADMIN";
}
