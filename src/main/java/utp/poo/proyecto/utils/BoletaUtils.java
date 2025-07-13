package utp.poo.proyecto.utils;

import utp.poo.proyecto.entities.personas.Cliente;
import utp.poo.proyecto.entities.personas.Vendedor;
import utp.poo.proyecto.entities.productos.Producto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BoletaUtils {

    // MÉTODO CORREGIDO
    public static String imprimirBoleta(Vendedor vendedor, Cliente cliente, List<Producto> productos) {
        double totalGeneral = 0;

        // 1. Primero, calculamos el TOTAL GENERAL de la venta.
        for (Producto p : productos) {
            // Aseguramos que el producto tenga cantidad y precio válidos para sumar
            if (p != null && p.getPrecioVenta() > 0 && p.getCantidad() > 0) {
                totalGeneral += p.getPrecioVenta() * p.getCantidad();
            }
        }

        // 2. A partir del TOTAL, desglosamos el Subtotal y el IGV.
        double subtotal = totalGeneral / 1.18;
        double igv = totalGeneral - subtotal;
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // 3. Usamos StringBuilder y String.format para una boleta bien alineada.
        StringBuilder boleta = new StringBuilder();
        boleta.append("=========================================\n");
        boleta.append("              CAFÉ AGUSTO\n");
        boleta.append("=========================================\n");
        boleta.append("Boleta de Venta Electrónica\n");
        boleta.append(String.format("Fecha: %s\n", fecha));
        boleta.append(String.format("Vendedor: %s\n", vendedor.getNombre()));
        boleta.append("-----------------------------------------\n");
        boleta.append(String.format("Cliente: %s\n", cliente.getNombre() != null && !cliente.getNombre().isEmpty() ? cliente.getNombre() : "Varios"));
        boleta.append(String.format("DNI: %s\n", cliente.getDni() != null ? cliente.getDni().toString() : "S/D"));
        boleta.append("-----------------------------------------\n");
        boleta.append(String.format("%-18s %4s %9s %9s\n", "Producto", "Cant", "P.Unit", "Importe"));
        boleta.append("-----------------------------------------\n");

        for (Producto p : productos) {
             if (p != null) {
                double importe = p.getPrecioVenta() * p.getCantidad();
                boleta.append(String.format("%-18.18s %4d S/ %7.2f S/ %7.2f\n",
                        p.getNombre(), p.getCantidad(), p.getPrecioVenta(), importe));
            }
        }
        
        boleta.append("-----------------------------------------\n");
        boleta.append(String.format("SUBTOTAL:              S/ %10.2f\n", subtotal));
        boleta.append(String.format("IGV (18%%):             S/ %10.2f\n", igv));
        boleta.append(String.format("TOTAL A PAGAR:         S/ %10.2f\n", totalGeneral));
        boleta.append("=========================================\n\n"); // Doble salto de línea para separar boletas en el archivo
        
        return boleta.toString();
    }
}