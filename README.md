# Academy_project_3
깃신(Gitsin)_GitHub daily challenge support application  


**앱 설명**
> 깃신 (Gitsin)
게이미피케이션 적용 및 크롤링을 이용한 자바 기반 GitHub 데일리 리마인드 소셜 애플리케이션 구현 (안드로이드 애플리케이션) 

**사용 언어 & 기술 & 도구**
> Language: Java, Jsoup, JSON
> IDE: Android Studio
> DB: Sqlite, Firebase
> 외부 라이브러리: Gradle, Maven
> 협업툴: Github (CI/CD), Notion

**기획내용**
1. 매일의 contribution 여부와 지속 contribution sterak 일수를 확인할 수 있는 회원 프로필 페이지 제공 (GitHub 프로필 크롤링)
2. GitHub 및 깃신 활동 내용 관련 업적 달성도 확인이 가능한 업적 페이지 구현
3. 유저간 contribution streak 유지 내용으로 경쟁이 가능한 챌린지 페이지 제공
4. 가입한 유저의 GitHub 및 깃신 활동을 비교할 수 있는 통계 페이지 제공 
5. 깃신 유저 리스트 확인 및 소셜(팔로우) 기능을 제공하는 친구 페이지 구현
6. GitHub 계정 정보, 알람 및 다크모드를 설정할 수 있는 설정 페이지 구현 

**애플리케이션 구현 화면**
<img width="50%" src="https://user-images.githubusercontent.com/72402916/138816309-fbeca859-f223-4f11-a974-d09063aceea3.png"/>
<img width="50%" src="https://user-images.githubusercontent.com/72402916/138816311-60d67f9c-cf99-4842-92f8-93c65292105d.png"/>

**메뉴 및 담당**

0. GitHub 프로필 크롤링 ------------------------ [함께]

1. 회원 ------------------------------------- [junkyu92]
- 회원가입, 로그인 기능 구현
- 오늘 contribution 여부, 현재 contribution streak 지속일수
- 가입일, GitHub 아이디 기재

1-1. 설정 페이지 ------------------------------ [ozestina]
- GitHub 계정 변경
- Notification 설정 (시간 설정)
- 다크모드 (Night Theme) 설정
- 로그아웃, 깃신 탈퇴

2. HoF (Hall of Fame) ---------------------- [hangnew]
- 유저 개인의 업적 달성 정도 표시
- streak 지속 정도
- 팔로우 수 정도
- 챌린지 참여 정도

3. 챌린지 페이지 ------------------------------ [ozestina]
- 참여한 챌린지 종류 확인
- 챌린지 리스트 확인 & 참여

4. 통계 페이지 -------------------------------- [hangnew]
- 깃신 유저의 활동 내용 통계 페이지
- 총 유저 수, 평균 연속 모내기, 평균 친구 수 데이터
- 항목 별 유저 랭킹 제공

5. 친구 페이지 ------------------------------- [junkyu92]
- 팔로우할 계정 찾기 & 팔로우 등록
- 팔로우 리스트 확인 & 팔로우 삭제
- 팔로워 리스트 확인

