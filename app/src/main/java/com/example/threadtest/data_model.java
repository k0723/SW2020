package com.example.threadtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_model {


    @SerializedName("days")
    @Expose
    private String days;

    @SerializedName("repeat")
    @Expose
    private String repeat;

    @SerializedName("hour")
    @Expose
    private String hour;

    @SerializedName("minute")
    @Expose
    private String minute;

    @SerializedName("nano")
    @Expose
    private String nano;

    @SerializedName("second")
    @Expose
    private String second;

    public void setDays(String days) {
        this.days = days;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setNano(String nano) {
        this.nano = nano;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getDays(){
        return days;
    }

    public String getRepeat(){
        return repeat;
    }

    public String getHour2(){
        return hour;
    }

    public String getMinute(){
        return minute;
    }

    public String getNano()
    {
        return nano;
    }

    public String getSecond()
    {
        return second;
    }


}
