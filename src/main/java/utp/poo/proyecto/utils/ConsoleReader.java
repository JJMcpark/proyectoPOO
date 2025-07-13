// NUEVO: Clase de utilidad para centralizar la lectura de datos de la consola.
package utp.poo.proyecto.utils;

import java.util.Scanner;

public class ConsoleReader {

    /**
     * Lee una cadena de texto, asegurando que no esté vacía.
     * Vuelve a solicitar la entrada hasta que se ingrese un valor válido.
     */
    public static String leerNoVacio(Scanner sc, String mensaje) {
        String entrada;
        System.out.print(mensaje);
        do {
            entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.print("Este campo es obligatorio. Intente de nuevo: ");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    /**
     * Lee una cadena de texto que puede ser opcional (puede estar vacía).
     */
    public static String leerOpcional(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    /**
     * Lee un entero, asegurando que sea un número válido dentro de un rango.
     * Vuelve a solicitar la entrada hasta que se ingrese un valor válido.
     */
    public static int leerOpcion(Scanner sc, String mensaje, int min, int max) {
        int opcion = -1;
        System.out.print(mensaje);
        while (true) {
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    System.out.print("Opción inválida. Ingrese un número entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Por favor, ingrese un número: ");
            }
        }
    }

    /**
     * Lee un entero positivo. Permite una entrada vacía para devolver null.
     */
    public static Integer leerEnteroPositivoNull(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (true) {
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) return null;
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= 0) {
                    return valor;
                } else {
                    System.out.print("Ingrese un valor positivo o deje en blanco: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Ingrese un número entero o deje en blanco: ");
            }
        }
    }

    /**
     * Lee un número decimal (double) positivo. Permite una entrada vacía para devolver null.
     */
    public static Double leerDoublePositivoNull(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (true) {
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) return null;
            try {
                double valor = Double.parseDouble(entrada);
                if (valor >= 0) {
                    return valor;
                } else {
                    System.out.print("Ingrese un valor positivo o deje en blanco: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Ingrese un número o deje en blanco: ");
            }
        }
    }
}   