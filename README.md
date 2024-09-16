# 쿠폰 발급 및 리더보드 시스템

이 프로젝트는 선착순 쿠폰 발급 사이트와 실시간 리더보드를 구현한 애플리케이션입니다. 사용자는 닉네임을 입력하여 쿠폰을 발급받고, 발급된 쿠폰은 리더보드에 실시간으로 업데이트됩니다.

## 기능

- **닉네임 입력**: 사용자는 자신의 닉네임을 입력하여 발급 페이지로 이동할 수 있습니다.
- **쿠폰 발급**: 발급 페이지에서 '쿠폰 발급하기' 버튼을 클릭하면 쿠폰이 발급됩니다.
- **리더보드 표시**: 리더보드는 실시간으로 업데이트되며, 최신 발급자 순위가 표시됩니다.
- **리더보드 초기화**: '초기화' 버튼을 클릭하여 리더보드를 비울 수 있습니다.

## 설치

프로젝트를 클론하고 의존성을 설치하려면 다음 단계를 따르세요.

1. **프로젝트 클론**

    ```bash
    git clone https://github.com/dhfkdlsj/leaderboard.git
    cd leaderboard
    ```

2. **의존성 설치**

    - **Spring Boot 애플리케이션**: Java와 Gradle을 사용하여 서버를 실행합니다.
    - **프론트엔드**: HTML과 JavaScript를 사용하여 클라이언트를 구현합니다.

## 실행 방법

1. **서버 실행**

    - Spring Boot 애플리케이션을 실행합니다.

    ```bash
    ./gradlew bootRun
    ```

2. **프론트엔드 실행**

    - 프론트엔드는 정적 HTML 파일을 포함하고 있으며, 서버가 실행되면 `localhost:8080`에서 접근할 수 있습니다.

## 엔드포인트

- **닉네임 입력 페이지**: `/`
- **발급 페이지**: `/issue.html`
- **리더보드 API**: `/api/leaderboard`
- **리더보드 초기화 API**: `/api/leaderboard/clear`

## 기술 스택

- **Spring Boot**: Java 기반의 백엔드 프레임워크
- **Redis**: 리더보드 데이터를 저장하기 위한 NoSQL 데이터베이스
- **WebSocket**: 실시간 데이터 전송
- **SockJS & STOMP**: WebSocket을 위한 클라이언트 라이브러리
- **HTML/CSS/JavaScript**: 프론트엔드 구현

## 개발자

- **강희준**: 
- **GitHub**: [https://github.com/dhfkdlsj](https://github.com/dhfkdlsj)


## 배포

아직 안함
