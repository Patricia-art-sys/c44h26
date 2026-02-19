package com.lyne.examen1;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
    private ArrayList <Evaluation> list;

    public Groupe() {
        list = new ArrayList<>();
    }

    public ArrayList<Evaluation> getList() {
        return list;
    }
    public void ajouterEvaluation(Evaluation e){
        list.add(e);
    }
    public int nombreEvaluation(){
        return list.size();
    }
    public String meilleureEvaluation(){
        int maxService = 0;
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getNbServices() > maxService){
                maxService = list.get(i).getNbServices();
                index = i;
            }
        }
        return list.get(index).getMatricule();
    }
}
