package org.example;

import org.example.procesos.EjemploCMD;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder builderF = new ProcessBuilder("cmd.exe", "/C","type","pokemon_names.txt");

        String salida;
        String concat = "";
       // while(( salida = p.inputReader().readLine()) !=null) concat += salida + "\n";

        ProcessBuilder builder = new ProcessBuilder("java", "-jar","PGV.jar","6");
        builder.directory(new File("out/artifacts/PGV_jar"));

        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        //Process p = builderF.start();
        Process grep = builder.start();

        //new Scanner(System.in).nextLine();

        System.out.println(grep.waitFor());
        //        p.waitFor();
       /* grep.outputWriter().write(concat);
        grep.outputWriter().newLine();
        grep.outputWriter().flush();*/


    }
}