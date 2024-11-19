package org.example.examen.lorca;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Lector implements Runnable {

    File ficheroLorca;
     ArrayList<Integer> listaVocales;
    static int totalVocales = 0;
    static int vocalesMayusculas = 0;
    char letraALeer;

    static ReentrantLock lockTotal =  new ReentrantLock(true);
    static Semaphore semaforoMayusculas = new Semaphore(1);

    public Lector(File ficheroLorca, char letraALeer, ArrayList<Integer> lista) {
        this.ficheroLorca = ficheroLorca;
        this.letraALeer = letraALeer;
        this.listaVocales = lista;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ficheroLorca));
            String linea = br.readLine();
            int pos = 0;
            switch (letraALeer){
                case 'a' -> pos = 0;
                case 'e' -> pos = 1;
                case 'i' -> pos = 2;
                case 'o' -> pos = 3;
                default -> pos = 4;
            }
            while (linea != null){
                for(Character c : linea.toCharArray()){
                    // letra -> a,e,i,o,u -> 0,1,2,3,4
                    if(c == letraALeer || c == Character.toUpperCase(letraALeer)){
                        int num = listaVocales.get(pos) +1;
                        listaVocales.set(pos , num);
                        // listaVocales[pos]++;

                        lockTotal.lock();
                        totalVocales++;
                        lockTotal.unlock();


                        if(Character.isUpperCase(c)) {
                            semaforoMayusculas.acquire();
                            vocalesMayusculas++;
                            semaforoMayusculas.release();
                        }
                    }
                }
                linea = br.readLine();
            }
            System.out.println("La vocal " + letraALeer + " ha aparecido un total de " + listaVocales.get(pos) + " veces");
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
