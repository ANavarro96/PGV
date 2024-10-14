package org.example.procesos.practica1;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LanzadorProcesos {

    // Ruta relativa a mi jar
    static final String RUTA_JAR = "./out/artifacts/PGV_buscadorPalabra/PGV.jar";
    // Ruta a la carpeta de ficheros
    static final String RUTA_FICHERO = "./src/main/java/org/example/procesos/practica1/ficheros/";
    // Ruta a la carpeta de resultados
    static final String RUTA_RESULTADO = "./src/main/java/org/example/procesos/practica1/resultados/";
    public static void main(String[] args) {
        try{
            ProcessBuilder constructor;
            ArrayList<Process> listaProcesos = new ArrayList<>();
            // Para cada fichero de la carpeta...
            for(File f : new File(RUTA_FICHERO).listFiles()){
                //  Genero el comando, pasando las rutas actualizadas con los ficheros
                // Hay otras formas de hacer esto
                constructor = new ProcessBuilder("java","-jar",RUTA_JAR,
                        f.getAbsolutePath(), RUTA_RESULTADO + f.getName());
                constructor.redirectError(ProcessBuilder.Redirect.INHERIT);
                // Lo añado a una lista...
                listaProcesos.add(constructor.start());
            }

            // ...y Cuando YA haya lanzado todos, ahora es cuando espero!!
            // No hay que lanza y justo después esperar, eso los bloquea, provocando una ejecución secuencial!
            for (Process p : listaProcesos) p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException IE){
            IE.printStackTrace();
        }



    }
}
