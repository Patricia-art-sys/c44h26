package com.lyne.revisions_exam1;

public class Exercice {
    private int minutes;
    private String type;

    public Exercice(int minutes) {
        this.minutes = minutes;
        setType(type);
    }

    public int getMinutes() {
        return minutes;
    }

    public String getType() {
        return type;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setType(String type) {
        this.type = type;
    }
}
