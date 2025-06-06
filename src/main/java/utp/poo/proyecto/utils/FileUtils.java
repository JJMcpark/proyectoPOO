package utp.poo.proyecto.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void guardarTexto(String ruta, String texto, boolean append) {
        try (FileWriter writer = new FileWriter(ruta, append)) {
            writer.write(texto + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> leerLineas(String ruta) {
        try {
            return Files.readAllLines(Paths.get(ruta));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}