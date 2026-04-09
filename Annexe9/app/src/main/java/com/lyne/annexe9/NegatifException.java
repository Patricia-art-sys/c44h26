package com.lyne.annexe9;

public class NegatifException extends Exception{
    //inclut donc un message, getMessage
    private double valeurErronee;
    private String variableException;

    public NegatifException(double valeurErronee, String variableException) {
        //appel du construteur d'Exception qui prend en paramètre un message
        super(variableException + " a la valeur " + valeurErronee + " qui est négative. Recommecez");
        this.valeurErronee = valeurErronee;
        this.variableException = variableException;
    }
}
