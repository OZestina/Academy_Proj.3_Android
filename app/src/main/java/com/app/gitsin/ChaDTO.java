package com.app.gitsin;

public class ChaDTO {

    private String category;
    private int limit;
    private String name;
    private String participants;
    private String startDate;

    public ChaDTO() { }

    public ChaDTO(String category, int limit, String name, String participants, String startDate) {
        this.category = category;
        this.limit = limit;
        this.name = name;
        this.participants = participants;
        this.startDate = startDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "ChaDTO{" +
                "category='" + category + '\'' +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                ", participants='" + participants + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
