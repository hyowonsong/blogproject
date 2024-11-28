package com.blogproject.api.config;

import com.blogproject.api.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthInterceptor())
//                .addPathPatterns("/..", "/")
//                .excludePathPatterns("/error", "/favicon.ico");
//    }

    private final SessionRepository sessionRepository;
    private final AppConfig appConfig;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthResolver(sessionRepository, appConfig));
    }
}
