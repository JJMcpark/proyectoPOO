package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utp.poo.proyecto.utils.ConsoleReader;
import utp.poo.proyecto.utils.FileUtils;
import utp.poo.proyecto.entities.productos.*;
import java.util.Scanner;

@Service
public class KardexServiceImp implements KardexService {

    private final ComprobanteService comprobanteService;
    private static final String RUTA_KARDEX = "data/kardex/kardex.txt";

    public KardexServiceImp(ComprobanteService comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

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
        String tipo = ConsoleReader.leerNoVacio(sc, "Tipo de movimiento (ENTRADA/SALIDA): ");

        // Y aquí simplemente usas el servicio que fue "inyectado" en el constructor.
        // Fíjate que al método crearProductoInteractivo SOLO le pasas el Scanner.
        Producto producto = this.comprobanteService.crearProducto(sc);

        if (producto == null || producto.getCantidad() <= 0) {
            System.out.println("Registro de movimiento cancelado.");
            return;
        }

        String observacion = ConsoleReader.leerOpcional(sc, "Observación: ");
        registrarMovimientoInventario(tipo.toUpperCase(), producto, producto.getCantidad(), observacion, producto.getPrecioVenta());
    }

}
