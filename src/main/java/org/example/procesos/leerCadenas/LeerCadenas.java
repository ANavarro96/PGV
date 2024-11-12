package org.example.procesos.leerCadenas;

import java.util.Scanner;

public class LeerCadenas {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String input;

            System.out.println("Introduce cadenas. Escribe '*' para terminar:");

            while (!(input = scanner.nextLine()).equals("*")) {
                System.out.println("Leído: " + input);
            }

            scanner.close();
            System.out.println("Programa terminado.");
        }
    }

