package com.app.gitsin;

public class User {
    private String userId;
    private String userPw;
    private String signDate;

    //가장 최근 로그인 날짜 (streak기준일)
    private String streakCheckStart;
    //맥시멈 streak & counting용 변수
    private int maxStreak;
    //현재 streak
    private int streakToday;
    //오늘 contribute 여부
    private int todayCount;


    public User() {
    }

    public User(String userId, String userPw, String signDate) {
        this.userId = userId;
        this.userPw = userPw;
        this.signDate = signDate;
    }

    public User(String userId, String userPw, String signDate, String streakCheckStart, int maxStreak, int streakToday, int todayCount) {
        this.userId = userId;
        this.userPw = userPw;
        this.signDate = signDate;
        this.streakCheckStart = streakCheckStart;
        this.maxStreak = maxStreak;
        this.streakToday = streakToday;
        this.todayCount = todayCount;
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
                ", streakCheckStart='" + streakCheckStart + '\'' +
                ", maxStreak=" + maxStreak +
                ", streakToday=" + streakToday +
                ", todayCount=" + todayCount +
                '}';
    }
}
