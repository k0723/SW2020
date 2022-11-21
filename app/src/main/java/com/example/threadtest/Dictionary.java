package com.example.threadtest;


import java.util.HashMap;

public class Dictionary {



    private boolean[] weekend = new boolean[6];
    private int id;
    private int AM;
    private int Hour;
    private int Min;

    public Dictionary(int id, int AM, int Hour, int Min,boolean[] weekend ) {
        this.AM = AM;
        this.Hour = Hour;
        this.Min = Min;
        this.id = id;
        this.weekend = weekend;
    }

    public boolean[] getWeekend() {
        return weekend;
    }

    public void setWeekend(boolean[] weekend) {
        this.weekend = weekend;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAM(int AM) {
        this.AM = AM;
    }

    public int getAM() {
        return AM;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int Hour) {
        Hour = Hour;
    }

    public int getMin() {
        return Min;
    }

    public void setMin(int Min) {
        Min = Min;
    }
}
