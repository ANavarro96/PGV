package org.example.hilos;

import java.util.ArrayList;
import java.util.Random;

public class Launcher{

    public static void main(String[] args) {
        // cre
        ArrayList<JugadorSecuencial> listaMataos = new ArrayList<>();

        for(int i = 0; i < 10;i++) listaMataos.add(new JugadorSecuencial(new Random().nextInt(10000) + 1
                ,"Jugador " + i));

        for (JugadorSecuencial js : listaMataos ) {
            js.entrarACanasta();
        }

        System.out.println("Un total de " + listaMataos.stream().map(JugadorSecuencial::getTiempo).reduce(0, Integer::sum)/1000);

    }
}
