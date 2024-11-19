package org.example.examen.gatitos;

public class Comida {

    enum TIPO {PATE,BOLITAS,MOUSSE}
    enum TAMANYO {GRANDE,PEQUENYO}

    TIPO tipoComida;
    TAMANYO tamanyoBOL;

    public Comida(TIPO tipoComida, TAMANYO tamanyoBOL) {
        this.tipoComida = tipoComida;
        this.tamanyoBOL = tamanyoBOL;
    }
}
