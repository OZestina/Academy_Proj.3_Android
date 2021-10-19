# Academy_project_3
깃신(Gitsin)_Github daily challenge support application


**앱 설명**
> 깃신 (Gitsin)
게이미피케이션을 적용한 깃허브 contribution 챌린지 푸시 알람 및 소셜 애플리케이션 (깃허브 로그인 연동 및 크롤링 이용)  
레퍼런스: 듀오링고(Duolingo)

**사용 언어 & 기술 & 도구**
> Java
> Sqlite, Firebase
> Android Studio
> Github, Notion

**기획내용**
> 모내기 챌린지 (1일 1깃허브커밋 챌린지)  
> 깃헙 데일리 리마인드: 원하는 시간에 알림(배너, 푸시 등)으로 리마인드 당해보세요!
> 소셜기능: 팔로우

**메뉴 및 담당**

0. (최초실행 시) 로그인 페이지 (깃헙연동 로그인)     - junkyu92

1. 메인-회원 프로필                                - junkyu92
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

 
 
**하기내용 추후 정리 예정!**

[필요 기능]
1) 크롤링 자바클래스 - 모내기 크롤링               - [together!]

2) 챌린지                                                    - ozestina

- 길드전) 일정 기간 내 가장 많은 contributions 길드 우승

- 개인전) 최후의 1인
- 솔플) 7일 챌린지 / 30일 챌린지

- pvp) 1:1 

3) 업적:                                                      - hangnew
- 하루에 n개 올리기(1개, 5개, 10개)
- n일 연속 모내기 (3일, 7일, 14일, 30일, 100일, 365일)
- 친구n명 팔로우 (1명, 3명)
- 팔로워 n명 달성 (1명, 3명, 10명, 100명)
- MBTI등록

- 길드
- 언어사용
native(1개언어 100개 커밋)
bilingual(2개 언어 각각 100개씩 커밋)
multi(n개 언어 100개 이상씩 커밋),
- (챌린지) 최후의 1인, 
- (광고받을 시) 광고 클릭 높은 열혈유저

 

 

3. DB가 필요한 부분
1) 유저: 아이디, 패스워드, 팔로워, 팔로잉, 달력, 최대 streak, 현재까지 총 commit량, 업적달성(json), 광고클릭
2) 업적: idx, 내용 → (폰에 저장)

3) 챌린지: idx, 참여자, 시작일, 조건

4) 길드: idx, 길드명, 길드장, 길드원, 생성일
