package com.example.threadtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_model {

    @SerializedName("repeat")
    @Expose
    private boolean repeat;

    @SerializedName("hour")
    @Expose
    private String hour;

    @SerializedName("minute")
    @Expose
    private String minute;

    @SerializedName("nano")
    @Expose
    private int nano;

    @SerializedName("second")
    @Expose
    private String second;

    @SerializedName("days")
    @Expose
    private String days;

    public void setDays(String days) {
        this.days = days;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setNano(int nano) {
        this.nano = nano;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getDays(){
        return days;
    }

    public boolean getRepeat(){
        return repeat;
    }

    public String getHour2(){
        return hour;
    }

    public String getMinute(){
        return minute;
    }

    public int getNano() {return nano;}

    public String getSecond()
    {
        return second;
    }

}
