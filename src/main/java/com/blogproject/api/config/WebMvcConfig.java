package com.blogproject.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthInterceptor())
//                .addPathPatterns("/..", "/")
//                .excludePathPatterns("/error", "/favicon.ico");
//    }

//    private final SessionRepository sessionRepository;
    private final AppConfig appConfig;

    // 스프링 시큐리티를 적용하기 때문에 삭제
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new AuthResolver(sessionRepository, appConfig));
//    }
}
