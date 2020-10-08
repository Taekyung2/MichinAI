# 미국친구AI

> 카카오톡을 기반으로 대화를 통해 영어 공부를 할 수 있는 인공지능 챗봇 서비스



## 📁 목차

- [개요](##개요)
- [기능](##기능)
- [기술 스택](##기술-스택)
- [기술 설명](##기술-설명)
- [테스트 방법](##테스트-방법)



## ✍ 개요

> 영어 공부의 필요성은 느끼지만 시간적 여유가 없을 때 😥
>
> => 채팅 앱을 통해 영어 대화를 하며 틈틈히 공부하면 어떨까? 
>
> => 대화 기록을 통해 피드백을 제공하면 더 도움이 되지 않을까?



## 📌 기능

- 미친AI와 대화하기
  - 카카오톡에서 `미국친구AI` 친구 추가 후, `대화하기` 클릭  
  <img src="../readmeImgs/1.png" width="300">
  <img src="../readmeImgs/2.png" width="300">

  - 홈페이지의 `채팅 기록` 탭에서 날짜 별 채팅 기록 확인 가능
  <img src="../readmeImgs/3.png" width="300">
  <img src="../readmeImgs/4.png" width="300">
- 오늘의 회화
  - 네이버 크롤링을 통해, 1년 이내의 오늘의 회화 확인 가능
  <img src="../readmeImgs/6.png" width="300">
  <img src="../readmeImgs/5.png" width="300">
- 단어장
  - 기본 단어장 제공, 개인 단어장 생성 가능
  <img src="../readmeImgs/7.png" width="300">
  <img src="../readmeImgs/8.png" width="300">
  <img src="../readmeImgs/9.png" width="300">
  - 단어장 단어 기반 단어 퀴즈 
  <img src="../readmeImgs/11.jpg" width="300">
- 채팅 점수
  - 미친AI와의 대화에서 나타난 철자, 문법, 문맥 등의 오류
  <img src="../readmeImgs/10.png" width="300">



## 📌 기술 스택

### Front-End

- HTML/JS/CSS
- Vue/Vuetify

### Back-End

- Spring Boot
- Docker
- Jenkins
- NGINX

### DataBase

- MariaDB
- MongoDB



## 📌 기술 설명

### 주요 기술

- Kakao i open builder

  > 카카오톡 채널의 챗봇 설계가 가능한 카카오 AI 설계 플랫폼

- ParlAI

  >  챗봇 개발을 위한 대화 AI 모델의 공유, 훈련 및 테스트 환경을 제공하는 프레임워크

- LT (Language-Toop Open Source)

  > 영어 포함 20개 이상의 언어의 Spelling, Grammar 검사를 제공하는 Open Source



## 📌 테스트 방법  

💥 테스트를 하기 위해

>1. 카카오톡에서 미국친구AI 친구 추가
>
>2. 웹 연동하기 과정 필요



💥 미친AI가 궁금하다면?

> 1. 카카오톡에서 미국친구AI와 채팅하기
>
> 2. https://michinai.cf 방문하기