package org.example.procesos;

import java.io.*;
import java.util.Scanner;

public class EjemploCMD {

    public static void hacerPing(){

        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/C","ping", "google.com"); // o "dir" en Windows
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void recibirValores(){
        Scanner s = new Scanner(System.in);

        System.out.println("Hola " + s.nextLine());

    }
    public static void redirigirIO() throws IOException, InterruptedException {
        File inputFile = new File("southpark.txt");
        File outputFile = new File("output.txt");

        ProcessBuilder builder = new ProcessBuilder("sort");
        builder.redirectInput(inputFile);
        builder.redirectOutput(outputFile);

        Process process = builder.start();
        int exitCode = process.waitFor();
        System.out.println("Proceso finalizado con código: " + exitCode);
    }


    public static void llamarProceso() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar","PGV.jar");
        builder.directory(new File("out/artifacts/PGV_jar"));

        Process process = builder.start();
        BufferedWriter entrada = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        BufferedReader salida = new BufferedReader(new InputStreamReader(process.getInputStream()));
        entrada.write("Juanito");
        entrada.newLine();
        entrada.flush();

        System.out.println(salida.readLine());


        int exitCode = process.waitFor();
        System.out.println("Proceso finalizado con código: " + exitCode);
    }
    public static void mandarUnaCosaAOtra() throws IOException, InterruptedException {
        ProcessBuilder constructor = new ProcessBuilder("cmd.exe","/C","type", "southpark.txt");

        Process p = constructor.start();



        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        /*String linea = br.readLine();
        String concat = "";
        while(linea != null){
            concat += linea;
            System.out.println(linea);
            linea = br.readLine();
        }*/

        ProcessBuilder constructorGrep = new ProcessBuilder("cmd.exe","/C","find", "ninja");
        constructor.redirectOutput(constructorGrep.redirectOutput());
        Process grep = constructorGrep.start();

        /*BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(grep.getOutputStream()));
        bw.write(concat);bw.newLine();bw.flush();*/

        br = new BufferedReader(new InputStreamReader(grep.getInputStream()));

        String linea = br.readLine();
        String concat = "";
        while(linea != null){
            concat += linea;
            System.out.println(linea);
            linea = br.readLine();
        }

        br.close();
        p.waitFor();



    }

    public static void controlarTiempo(long mili){
        try {
            ProcessBuilder builder = new ProcessBuilder("notepad.exe","ostras");
            Process process = builder.start();

            // Monitorear el proceso y terminarlo si tarda demasiado
            long startTime = System.currentTimeMillis();
            System.out.println(process.pid());
            /*while (process.isAlive()) {
                if (System.currentTimeMillis() - startTime > 5000) { // 5 segundos
                    process.destroy();
                    System.out.println("Proceso terminado por tardar demasiado.");
                    break;
                }
                Thread.sleep(500);
            }*/

            int exitCode = process.waitFor();
            System.out.println("Código de salida: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void miGrep(){
        Scanner s = new Scanner(System.in);

        while(s.hasNextLine()){
            System.out.println(s.nextLine());
        }

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            // Comporbar numero de parametros de la entrada
            System.out.println("Parametros incorrectos");
            System.exit(1);
        } else {
            String frase = args[0];
            if (frase.chars().anyMatch(Character::isDigit)){
                System.exit(2);
            }
            // Todo correcto: mostramos el argumento pasado en mayúsculas
            System.out.println(frase.toUpperCase());
            System.exit(0);
        }
    }
}
