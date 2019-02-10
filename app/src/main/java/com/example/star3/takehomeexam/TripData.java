package com.example.star3.takehomeexam;

/**
 * Created by star3 on 2018-04-18.
 */

public class TripData {
    private String title;
    private String content;
    private double x1;
    private double y1;

    public void setTitle(String t) {
        this.title = t;
    }
    public void setContent(String c) {
        this.content = c;
    }

    public void setX(double x) {
        this.x1 = x;
    }
    public void setY(double y) {
        this.y1 = y;
    }

    public String getTitle() {
        return this.title;
    }
    public String getContent() {
        return this.content;
    }

    public double getX() {
        return this.x1;
    }
    public double getY() {return this.y1;}
}