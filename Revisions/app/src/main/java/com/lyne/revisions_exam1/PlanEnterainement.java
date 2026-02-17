package com.lyne.revisions_exam1;

import java.util.ArrayList;

public class PlanEnterainement {
    private ArrayList <Exercice> list;
    private int objectif;

    public PlanEnterainement(int objectif) {
        list = new ArrayList<>();
        this.objectif = objectif;
    }

    public int getObjectif() {
        return objectif;
    }

    public ArrayList<Exercice> getList() {
        return list;
    }

    public void ajouterExercice(Exercice ex){
        list.add(ex);
    }
    public int totalMinutes(){
        int total=0;
        for(int i=0; i <list.size(); i++){
            total += list.get(i).getMinutes();
        }
        return total;
    }

    public boolean objectifAtteint(){
        return true;
    }
}
