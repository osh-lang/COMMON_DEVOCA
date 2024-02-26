# DEVoca-api

REST API server for DEVoca

## 실행 방법

### 실행 전 필요한 사항

- 환경 변수 확인
  - [deploy/README.md](../deploy/README.md) 참고하여 `.env` 파일 생성.
  - IDE, 실행환경 별 환경변수에 추가.

### 빌드, 실행

```bash
$ ./gradlew bootJar
$ ./gradlew bootRun
```

- Go to <http://localhost/swagger-ui.html>

### 프로젝트로써의 실행

- java 17
- Gradle
- Intellij IDEA 권장
- [Lombok 설치 및 어노테이션 설정](https://inpa.tistory.com/entry/IntelliJ-%F0%9F%92%BD-Lombok-%EC%84%A4%EC%B9%98-%EB%B0%A9%EB%B2%95-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0)
- [redis 설치 및 환경변수 설정](https://github.com/microsoftarchive/redis/releases)

## 디렉토리 구조

```bash
DEVoca-api
├─resources
│  └─sql        # DB 초기화, 샘플 데이터 생성 등에 사용되는 SQL 파일
└─src
    ├─main
    │  ├─generated
    │  ├─java
    │  │  └─com
    │  │      └─ssafy
    │  │          └─devoca
    │  │              ├─board
    │  │              │  ├─controller
    │  │              │  ├─model
    │  │              │  │  └─mapper
    │  │              │  └─service
    │  │              ├─card
    │  │              │  ├─controller
    │  │              │  ├─model
    │  │              │  │  └─mapper
    │  │              │  └─service
    │  │              ├─config
    │  │              └─word
    │  │                  ├─controller
    │  │                  ├─model
    │  │                  │  └─mapper
    │  │                  └─service
    │  └─resources
    │      ├─mapper
    │      │  ├─board
    │      │  └─card
    │      └─mapper.word
    └─test
        └─java
            └─com
                └─ssafy
                    └─devoca
```

## Contributors

- [배성연](https://github.com/bae2019)
- [류지윤](https://github.com/Ryujy)
- [김주이](https://github.com/jjuyii49)
