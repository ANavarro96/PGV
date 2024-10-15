package org.example.hilos;

public class JugadorHilo implements Runnable{
    private String nombre;
    private int tiempo;

    public JugadorHilo(int tiempo, String nombre) {
        this.tiempo = tiempo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        System.out.println("Yo," + nombre + " entro a canasta LA MACHACO DE ESPALDAS dame " + tiempo / 1000 + " que estoy viejo");
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nombre +  "ya he terminado vaya mate");
    }




}
