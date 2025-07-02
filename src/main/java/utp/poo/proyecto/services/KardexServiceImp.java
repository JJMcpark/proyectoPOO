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
        String tipo = sc.nextLine().trim();
        // Solo permite Insumo o Producto genérico
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Insumo");
        System.out.println("2. Otro producto");
        System.out.print("Opción: ");
        int tipoProducto = 0;
        while (tipoProducto < 1 || tipoProducto > 2) {
            String opcionStr = sc.nextLine().trim();
            try {
                tipoProducto = Integer.parseInt(opcionStr);
                if (tipoProducto < 1 || tipoProducto > 2) {
                    System.out.print("Opción inválida. Ingrese 1 o 2: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese 1 o 2: ");
            }
        }
        Producto producto = null;
        if (tipoProducto == 1) {
            producto = new Insumo();
            System.out.print("Nombre del insumo: ");
            String nombreInsumo = sc.nextLine().trim();
            producto.setNombre(nombreInsumo.isEmpty() ? null : nombreInsumo);
        } else {
            producto = new Producto() {}; // Producto anónimo si es abstracto
            System.out.print("Nombre del producto: ");
            String nombreProd = sc.nextLine().trim();
            producto.setNombre(nombreProd.isEmpty() ? null : nombreProd);
        }
        System.out.print("Cantidad: ");
        producto.setCantidad(leerEnteroPositivoNull(sc));
        System.out.print("Precio unitario: ");
        producto.setPrecioVenta(leerDoublePositivoNull(sc));
        System.out.print("Observación: ");
        String observacion = sc.nextLine().trim();
        registrarMovimientoInventario(tipo, producto, producto.getCantidad(), observacion, producto.getPrecioVenta());
    }

    private Integer leerEnteroPositivoNull(Scanner sc) {
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return null;
        try {
            int valor = Integer.parseInt(entrada);
            return valor >= 0 ? valor : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double leerDoublePositivoNull(Scanner sc) {
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return null;
        try {
            double valor = Double.parseDouble(entrada);
            return valor >= 0 ? valor : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
