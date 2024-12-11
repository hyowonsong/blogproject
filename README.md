## 블로그 프로젝트

- **프로젝트 기간:** 2024/11/20 ~ 2024/12/08
- **링크 :** https://github.com/hyowonsong/blogproject
- **서비스 소개:** Spring Boot 기반 RESTful 블로그 서비스
- **기술 스택**
    - **Backend**: Spring Boot 3.x, Java 17
    - **ORM**: JPA, QueryDSL
    - **Security**: Spring Security, JWT
    - **Database**: H2 (개발)
    - **Build Tool**: Gradle
    - **Testing**: JUnit 5, Spring REST Docs
- **프로젝트 인원:** 1명 (개인 프로젝트)
- **핵심 기능**
    1. **인증 및 보안 시스템** 
        - Spring Security 기반 인증/인가 구현
        - JWT 토큰 기반 stateless 인증
        - 비밀번호 BCrypt 암호화
    
    **2. 도메인 모델 및 REST API**
    
    - 핵심 도메인 : 사용자(User), 게시글(Post), 댓글(Comment)
    - API 설계 원칙 : RESTful 아키텍처 준수, 상태 코드 및 에러 응답 표준화
    
    **3. 데이터 접근 및 영속성**
    
    - ORM 최적화 전략
        - JPA 엔티티 매핑
        - QueryDSL을 통한 복잡한 쿼리 최적화
        - 트랜잭션 관리
    
    **4. 예외 처리 및 검증**
    
    - 전역 예외 처리 (`@ControllerAdvice`)
    - 커스텀 예외 클래스
    - Bean Validation을 통한 데이터 검증
    
    **5. 테스트 전략**
    
    - 단위 테스트 (Service, Repository)
    - 통합 테스트 (Controller)
    - Spring REST Docs를 활용한 API 문서화
- **프로젝트 문제 해결 과정**
    1. 인증 시스템 구현 :  JWT + Spring Security 를 사용
    2. 보안 취약점 대응 : 비밀번호 해시 (BCrypt)
