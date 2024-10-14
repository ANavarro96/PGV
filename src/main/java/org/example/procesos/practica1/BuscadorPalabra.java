package org.example.procesos.practica1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BuscadorPalabra {

    public static void main(String[] args) throws IOException {
        if(args.length != 2) System.exit(-1);
        // Aquí le paso al proceso el fichero de lectura y el de escritura.
        File fichero = new File(args[0]);
        File resultado = new File(args[1]);

        if(!resultado.exists()) resultado.createNewFile();

        // Abro los flujos de entrada / salida de los ficheros.
        try (BufferedReader br = new BufferedReader(new FileReader(fichero));
           BufferedWriter bw = new BufferedWriter(new FileWriter(resultado))) {
            // Cojo las palabras y me las paso a una lista de strings
            ArrayList<String> palabras = br.lines().flatMap(s ->  Arrays.stream(s.split(" ")))
                    .collect(Collectors.toCollection(ArrayList::new));

            HashMap<String, Integer> diccionarioPalabras = new HashMap<>();

            // añado cada palabra a un diccionario
            palabras.forEach(palabra -> {
                diccionarioPalabras.put(palabra, diccionarioPalabras.getOrDefault(palabra,-1) + 1);
            });

            // Obtengo el máximo
            String msg = "La palabra más repetida en " +
                    "" + args[0].substring(args[0].lastIndexOf("\\")) + " es " + diccionarioPalabras.entrySet().stream().max(Map.Entry.comparingByValue())
                    .get();

            // Lo escribo en el fichero de salida.
            bw.write(msg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
