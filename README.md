# 쿠폰 발급 및 리더보드 시스템

이 프로젝트는 선착순 쿠폰 발급 사이트와 실시간 리더보드를 구현한 애플리케이션입니다. 사용자는 닉네임을 입력하여 쿠폰을 발급받고, 발급된 쿠폰은 리더보드에 실시간으로 업데이트됩니다.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)

## 기능

- **닉네임 입력**: 사용자는 자신의 닉네임을 입력하여 발급 페이지로 이동할 수 있습니다.
- **쿠폰 발급**: 발급 페이지에서 '쿠폰 발급하기' 버튼을 클릭하면 쿠폰이 발급됩니다.
- **리더보드 표시**: 리더보드는 실시간으로 업데이트되며, 최신 발급자 순위가 표시됩니다.
- **리더보드 초기화**: '초기화' 버튼을 클릭하여 리더보드를 비울 수 있습니다.

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
