package utp.poo.proyecto.utils;

import utp.poo.proyecto.entities.personas.Cliente;
import utp.poo.proyecto.entities.personas.Vendedor;
import utp.poo.proyecto.entities.productos.Producto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BoletaUtils {
    public static String imprimirBoleta(Vendedor vendedor, Cliente cliente, List<Producto> productos) {
        double subtotal = 0;
        for (Producto p : productos) {
            subtotal += p.getPrecioVenta() * p.getCantidad();
        }
        double igv = subtotal * 0.18;
        double total = subtotal + igv;
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        StringBuilder boleta = new StringBuilder();
        boleta.append("Fecha: ").append(fecha).append("\n");
        boleta.append("Vendedor: ").append(vendedor.getNombre()).append("\n");
        boleta.append("Cliente: ").append(cliente.getNombre()).append(" | DNI: ").append(cliente.getDni()).append("\n");
        boleta.append("Productos:\n");
        for (Producto p : productos) {
            boleta.append("- ").append(p.getNombre()).append(" x").append(p.getCantidad())
                  .append(" @ S/ ").append(p.getPrecioVenta())
                  .append(" = S/ ").append(p.getPrecioVenta() * p.getCantidad()).append("\n");
        }
        boleta.append("SUBTOTAL: S/ ").append(String.format("%.2f", subtotal)).append("\n");
        boleta.append("IGV (18%): S/ ").append(String.format("%.2f", igv)).append("\n");
        boleta.append("TOTAL: S/ ").append(String.format("%.2f", total)).append("\n");
        boleta.append("=======================================\n");
        return boleta.toString();
    }
}