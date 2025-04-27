# UserService Directory Structure

```plaintext
src/main/java/com/example/userservice/
├── adapter/
│   ├── in/
│   │   └── web/
│   │       └── security/
│   │           ├── authorization/
│   │           │   ├── AuthorizationExtractor.java        // Authorization 헤더 또는 쿠키 추출 전략 인터페이스
│   │           │   ├── BearerAuthorizationExtractor.java   // Bearer 토큰 방식 Authorization 추출 구현체
│   │           │   └── CookieAuthorizationExtractor.java   // Cookie 방식 Authorization 추출 구현체
│   │           ├── jwt/
│   │           │   ├── JwtTokenProvider.java               // JWT 생성 및 검증 유틸리티
│   │           ├── CustomAuthenticationEntryPoint.java     // 인증 실패 시 처리 핸들러
│   │           ├── CustomAccessDeniedHandler.java          // 인가 실패 시 처리 핸들러
│   │           ├── JwtAuthenticationFilter.java            // JWT 기반 인증 필터
│   │           └── SecurityConfig.java                     // Spring Security 설정 클래스
│   ├── out/
│   │   ├── persistence/
│   │   │   └── UserPersistenceAdapter.java                 // (예정) DB 연동 유저 저장소 어댑터
│   │   ├── security/
│   │   │   └── BcryptPasswordEncoderAdapter.java           // Bcrypt 암호화 어댑터
│   │   └── kafka/
│   │       └── UserEventPublisher.java                     // (예정) Kafka 유저 이벤트 발행 어댑터
│
├── application/
│   ├── port/
│   │   ├── in/
│   │   │   ├── RegisterUserUseCase.java                    // 유저 회원가입 유스케이스 인터페이스
│   │   │   ├── LoginUserUseCase.java                       // 유저 로그인 유스케이스 인터페이스
│   │   └── out/
│   │       ├── LoadUserPort.java                           // 유저 조회 포트 인터페이스
│   │       ├── SaveUserPort.java                           // 유저 저장 포트 인터페이스
│   │       ├── PasswordEncoderPort.java                    // 패스워드 인코더 포트 인터페이스
│   │       └── PublishUserEventPort.java                   // 유저 이벤트 발행 포트 인터페이스
│   └── service/
│       ├── UserService.java                                // 유저 유스케이스 구현 클래스
│
├── domain/
│   ├── model/
│   │   └── user/
│   │       ├── User.java                                   // 유저 도메인 엔티티
│   │       └── vo/
│   │           ├── Password.java                           // 비밀번호 값 객체
│   │           ├── Email.java                              // 이메일 값 객체
│   │           └── Nickname.java                           // 닉네임 값 객체
│   └── exception/
│       ├── ErrorCode.java                                  // 에러 코드 Enum 정의
│       └── CustomException.java                            // 사용자 정의 비즈니스 예외 클래스
│
├── config/
│   └── MapperConfig.java                                   // ModelMapper, ObjectMapper Bean 등록
```

---

> ✨ 이 Markdown 파일은 보기 쉽도록 각 파일 이름 + 설명을 1줄로 정리했습니다.
> ✨ 모든 주석은 1줄 이내로 끝나며 명확한 기능 설명만 추가했습니다.

