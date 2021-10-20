package com.app.gitsin;

public class User {
    String userId;
    String userPw;
    String signDate;

    public User() {
    }

    public User(String userId, String userPw, String signDate) {
        this.userId = userId;
        this.userPw = userPw;
        this.signDate = signDate;
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
                '}';
    }
}
