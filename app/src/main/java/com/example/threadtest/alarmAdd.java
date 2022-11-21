package com.example.threadtest;

import com.google.gson.annotations.SerializedName;

public class alarmAdd {

    @SerializedName("days")
    private String days;
    @SerializedName("repeat")
    private boolean repeat;
    @SerializedName("startTime")
    private String startTime;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public alarmAdd(String days, boolean repeat, String startTime) {
        this.days = days;
        this.repeat = repeat;
        this.startTime = startTime;
    }
}
