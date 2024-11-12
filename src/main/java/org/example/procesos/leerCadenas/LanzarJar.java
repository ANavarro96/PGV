package org.example.procesos.leerCadenas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LanzarJar {
    public static void main(String[] args) {
        try {

            // Ejecutar el archivo JAR
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "out/artifacts/PGV_lanzarJar/PGV.jar");
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            // processBuilder.inheritIO() -> así la entrada y salida del proceso hijo se convierten en las del padre, es decir, en estándar.
            Process process = processBuilder.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            bw.write("Hola que tal como estamos");
            bw.newLine();
            bw.flush();


            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
