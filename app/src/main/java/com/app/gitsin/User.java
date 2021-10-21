package com.app.gitsin;

public class User {
    private String userId;
    private String userPw;
    private String signDate;
    private String githubId;
    private String friends;

    //가장 최근 로그인 날짜 (streak기준일)
    private String streakCheckStart;
    //맥시멈 streak & counting용 변수
    private int maxStreak;
    //현재 streak
    private int streakToday;
    //오늘 contribute 여부
    private int todayCount;
    private int chaGroup;
    private int chaPerson;
    private int chaGroupDone;
    private int chaPersonDone;

    public User() {
    }

    public User(String userId, String userPw, String signDate, String githubId, String friends, String streakCheckStart, int maxStreak, int streakToday, int todayCount, int chaGroup, int chaPerson, int chaGroupDone, int chaPersonDone) {
        this.userId = userId;
        this.userPw = userPw;
        this.signDate = signDate;
        this.githubId = githubId;
        this.friends = friends;
        this.streakCheckStart = streakCheckStart;
        this.maxStreak = maxStreak;
        this.streakToday = streakToday;
        this.todayCount = todayCount;
        this.chaGroup = chaGroup;
        this.chaPerson = chaPerson;
        this.chaGroupDone = chaGroupDone;
        this.chaPersonDone = chaPersonDone;
    }

    public int getChaGroupDone() {
        return chaGroupDone;
    }

    public void setChaGroupDone(int chaGroupDone) {
        this.chaGroupDone = chaGroupDone;
    }

    public int getChaPersonDone() {
        return chaPersonDone;
    }

    public void setChaPersonDone(int chaPersonDone) {
        this.chaPersonDone = chaPersonDone;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public int getChaGroup() {
        return chaGroup;
    }

    public void setChaGroup(int chaGroup) {
        this.chaGroup = chaGroup;
    }

    public int getChaPerson() {
        return chaPerson;
    }

    public void setChaPerson(int chaPerson) {
        this.chaPerson = chaPerson;
    }

    public String getStreakCheckStart() {
        return streakCheckStart;
    }

    public void setStreakCheckStart(String streakCheckStart) {
        this.streakCheckStart = streakCheckStart;
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        this.maxStreak = maxStreak;
    }

    public int getStreakToday() {
        return streakToday;
    }

    public void setStreakToday(int streakToday) {
        this.streakToday = streakToday;
    }

    public int getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", signDate='" + signDate + '\'' +
                ", githubId='" + githubId + '\'' +
                ", friends='" + friends + '\'' +
                ", streakCheckStart='" + streakCheckStart + '\'' +
                ", maxStreak=" + maxStreak +
                ", streakToday=" + streakToday +
                ", todayCount=" + todayCount +
                ", chaGroup=" + chaGroup +
                ", chaPerson=" + chaPerson +
                '}';
    }
}
