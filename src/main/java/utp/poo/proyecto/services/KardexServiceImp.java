package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utp.poo.proyecto.utils.FileUtils;
import utp.poo.proyecto.entities.productos.*;
import java.util.Scanner;

@Service
public class KardexServiceImp implements KardexService {

    private static final String RUTA_KARDEX = "src/main/resources/kardex/kardex.txt";

    @Override
    public void registrarMovimientoInventario(String tipo, Producto producto, Integer cantidad, String observacion, Double precioUnitario) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double total = (cantidad != null && precioUnitario != null) ? cantidad * precioUnitario : 0.0;
        String linea = String.format(
            "%s | %s | Cantidad: %s | Precio unitario: S/ %s | Total: S/ %.2f | Observación: %s | Fecha: %s",
            tipo == null ? "null" : tipo.toUpperCase(),
            producto == null ? "null" : producto.getNombre(),
            cantidad == null ? "null" : cantidad,
            precioUnitario == null ? "null" : String.format("%.2f", precioUnitario),
            total,
            observacion == null ? "null" : observacion,
            fecha
        );
        FileUtils.guardarTexto(RUTA_KARDEX, linea + System.lineSeparator(), true);
        System.out.println("Movimiento registrado correctamente.");
    }

    @Override
    public void mostrarKardex() {
        List<String> registros = FileUtils.leerLineas(RUTA_KARDEX);
        if (registros.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
        } else {
            System.out.println("=== KARDEX ===");
            for (String linea : registros) {
                System.out.println(linea);
            }
            System.out.println("==============");
        }
    }

    @Override
    public void registrarMovimientoInventarioInteractivo(Scanner sc) {
        System.out.print("Tipo de movimiento (ENTRADA/SALIDA): ");
        String tipo = leerNoVacio(sc);

        System.out.println("1. Insumo");
        System.out.println("2. Otro producto");
        int tipoProducto = leerOpcion(sc, 1, 2);

        Producto producto;
        if (tipoProducto == 1) {
            producto = new Insumo();
            System.out.print("Nombre del insumo: ");
        } else {
            producto = new Producto() {};
            System.out.print("Nombre del producto: ");
        }
        String nombre = leerNoVacio(sc);
        producto.setNombre(nombre);

        System.out.print("Cantidad: ");
        Integer cantidad = leerEnteroPositivoNull(sc);
        producto.setCantidad(cantidad);

        System.out.print("Precio unitario: ");
        Double precio = leerDoublePositivoNull(sc);
        producto.setPrecioVenta(precio);

        System.out.print("Observación: ");
        String observacion = sc.nextLine();

        registrarMovimientoInventario(tipo, producto, cantidad, observacion, precio);
    }

    private String leerNoVacio(Scanner sc) {
        String entrada;
        do {
            entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) System.out.print("Este campo es obligatorio. Intente de nuevo: ");
        } while (entrada.isEmpty());
        return entrada;
    }

    private int leerOpcion(Scanner sc, int min, int max) {
        int opcion = -1;
        while (opcion < min || opcion > max) {
            System.out.print("Opción: ");
            String input = sc.nextLine().trim();
            try {
                opcion = Integer.parseInt(input);
                if (opcion < min || opcion > max) System.out.print("Opción inválida. ");
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido. ");
            }
        }
        return opcion;
    }

    private Integer leerEnteroPositivoNull(Scanner sc) {
        while (true) {
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) return null;
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= 0) return valor;
                System.out.print("Ingrese un valor positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private Double leerDoublePositivoNull(Scanner sc) {
        while (true) {
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) return null;
            try {
                double valor = Double.parseDouble(entrada);
                if (valor >= 0) return valor;
                System.out.print("Ingrese un valor positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
