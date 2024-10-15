package org.example.procesos;

public class DarLaVuelta {



    public static void main(String[] args) {

        // public int procesos.DarLaVuelta(String s){}

        if(args.length == 0){
            // return 1;
            System.exit(1);
        }
        else{
            // hay numeros?
            if(args[0].matches(".*\\d.*")){
                // return 2
                System.exit(2);
            }
            else{
                // dar la vuelta
                System.out.println(args[0].toUpperCase());
                System.exit(0);
            }

        }


    }
}
