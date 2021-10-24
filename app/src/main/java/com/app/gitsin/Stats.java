package com.app.gitsin;

// 통계용 DTO 클래스
public class Stats{

    private String userId;
    private int star;
    private int maxStreak;
    private int chaPersonDone;
    private int chaGroupDone;

    public Stats() {
    }

    public Stats(String userId, String stars, int maxStreak, int chaPersonDone, int chaGroupDone) {
        this.userId = userId;
        this.star = (stars.length() == 0) ? 0 : stars.split(",").length;
        this.maxStreak = maxStreak;
        this.chaPersonDone = chaPersonDone;
        this.chaGroupDone = chaGroupDone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(String stars) {
        this.star = (stars.length() == 0) ? 0 : stars.split(",").length;
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        this.maxStreak = maxStreak;
    }

    public int getChaPersonDone() {
        return chaPersonDone;
    }

    public void setChaPersonDone(int chaPersonDone) {
        this.chaPersonDone = chaPersonDone;
    }

    public int getChaGroupDone() {
        return chaGroupDone;
    }

    public void setChaGroupDone(int chaGroupDone) {
        this.chaGroupDone = chaGroupDone;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "userId='" + userId + '\'' +
                ", star=" + star +
                ", maxStreak=" + maxStreak +
                ", chaPersonDone=" + chaPersonDone +
                ", chaGroupDone=" + chaGroupDone +
                '}';
    }
}
