package com.lyne.travailfinal;

import java.util.Date;

public class Pointage {
    private int point;
    private Date date;

    public Pointage(int point) {
        this.point = point;
        this.date = new Date();
    }

    public int getPoint() {
        return point;
    }

    public Date getDate() {
        return date;
    }
}
