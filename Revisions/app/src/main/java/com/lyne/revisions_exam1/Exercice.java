package com.lyne.revisions_exam1;

public class Exercice {
    private int minutes;
    private String type;

    public Exercice(int minutes, String type) {
        this.minutes = minutes;
        this.type = type;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getType() {
        return type;
    }
}
