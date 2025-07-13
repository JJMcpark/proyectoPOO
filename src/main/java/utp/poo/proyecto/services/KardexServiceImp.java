// MEJORADO: Se ha refactorizado para usar ConsoleReader y para crear productos correctamente.
package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utp.poo.proyecto.utils.FileUtils;
import utp.poo.proyecto.utils.ConsoleReader; // MEJORADO: Se usa la nueva utilidad
import utp.poo.proyecto.entities.productos.*;
import java.util.Scanner;

@Service
public class KardexServiceImp implements KardexService {

    private static final String RUTA_KARDEX = "src/main/resources/kardex/kardex.txt";

    @Override
    public void registrarMovimientoInventario(String tipo, Producto producto, Integer cantidad, String observacion, Double precioUnitario) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double total = (cantidad != null && precioUnitario != null) ? cantidad * precioUnitario : 0.0;
        
        // MEJORADO: Formato de línea estandarizado para evitar saltos de línea incorrectos.
        String linea = String.format(
            "TIPO: %s | PRODUCTO: %s | CANTIDAD: %d | PRECIO_UNIT: S/ %.2f | TOTAL: S/ %.2f | OBSERVACION: %s | FECHA: %s",
            tipo,
            producto.getNombre(),
            cantidad,
            precioUnitario,
            total,
            observacion,
            fecha
        );
        FileUtils.guardarTexto(RUTA_KARDEX, linea, true); // Se guarda con 'append'
        System.out.println("Movimiento registrado correctamente en el Kardex.");
    }

    @Override
    public void mostrarKardex() {
        List<String> registros = FileUtils.leerLineas(RUTA_KARDEX);
        if (registros.isEmpty()) {
            System.out.println("No hay movimientos registrados en el Kardex.");
        } else {
            System.out.println("\n=== KARDEX ===");
            for (String linea : registros) {
                System.out.println(linea);
            }
            System.out.println("==============\n");
        }
    }

    @Override
    public void registrarMovimientoInventarioInteractivo(Scanner sc) {
        String tipo = ConsoleReader.leerNoVacio(sc, "Tipo de movimiento (ENTRADA/SALIDA): ");

        // MEJORADO: Se reutiliza la lógica de creación de producto del ComprobanteService.
        // Esto evita la creación incorrecta de un `new Producto()` abstracto.
        ComprobanteServiceImp tempService = new ComprobanteServiceImp();
        Producto producto = tempService.crearProductoInteractivo(sc);

        if (producto == null) {
            System.out.println("Registro de movimiento cancelado.");
            return;
        }

        // Se usa la cantidad y precio ya asignados al producto durante su creación.
        Integer cantidad = producto.getCantidad();
        Double precio = producto.getPrecioVenta();
        String observacion = ConsoleReader.leerOpcional(sc, "Observación: ");

        registrarMovimientoInventario(tipo.toUpperCase(), producto, cantidad, observacion, precio);
    }
}