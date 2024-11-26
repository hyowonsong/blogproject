package com.blogproject.api.config;

import com.blogproject.api.config.data.UserSession;
import com.blogproject.api.domain.Session;
import com.blogproject.api.exception.Unauthorized;
import com.blogproject.api.repository.SessionRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final SessionRepository sessionRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    // 쿠키를 꺼내와서 진행
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            log.error("servletRequest null");
            throw new Unauthorized();
        }

        Cookie[] cookies = servletRequest.getCookies();
        if (cookies.length == 0) {
            log.error("cookies null");
            throw new Unauthorized();
        }

        String accessToken = cookies[0].getValue();

        Session session = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(Unauthorized::new);

        return new UserSession(session.getUser().getId());
    }

    // 헤더에 넘어온 키값으로 인증 (1) -> 이제는 쿠키를 꺼내와서 진행해야 하기 때문에 수정(2)
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        String accessToken = webRequest.getHeader("Authorization");
//        if (accessToken == null || accessToken.equals("")) {
//            throw new Unauthorized();
//        }
//
//        Session session = sessionRepository.findByAccessToken(accessToken)
//                .orElseThrow(Unauthorized::new);
//
//        return new UserSession(session.getUser().getId());
//    }
}
