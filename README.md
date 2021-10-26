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

**메뉴 및 담당**

0. [함께] GitHub 프로필 크롤링

1. [junkyu92] 회원
- 오늘 했는 지 여부 
- 현재 모내기 지속일수
- 현재 진행중인 챌린지명
- 이번달 캘린더 색칠현황                            - ozestina
- 팔로워/팔로우 명수
- 업적 달성 리스트
- 사용언어

1-1. (팝업페이지) 설정페이지                        - ozestina

2. HoF (Hall of Fame)                             - hangnew
- 달성 업적 가장 많은 수
- 달성 업적 가장 많은 계정
- 하루에 가장 많이 올린 커밋 수
- 팔로우가 가장 많은 계정
- 등등등

2-1. (탭) 재미있는 통계 (MBTI별, 언어별 등등)       - ozestina
- 가장 끈기있는 유형 (mbti)
- (당신들 사용) 가장 많은 mbti 유저
- 사용언어비율(프로필 상 기록한 데이터)

3. 챌린지페이지                                    - ozestina
- 챌린지 검색 & 참여
- 챌린지 등록 (자동 참여)

4. 길드페이지                                      - hangnew
- 길드 검색 & 가입
- 길드 등록 & 삭제 (가입 일주일 이상 경과해야 길드 등록 가능)

5. 팔로워/팔로우리스트                              - junkyu92
- 팔로우할 계정 찾기 & 팔로우 등록
- 팔로우 리스트 확인 & 팔로우 삭제
- 팔로워 리스트 확인

