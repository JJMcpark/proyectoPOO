package utp.poo.proyecto.utils;

import utp.poo.proyecto.entities.personas.Cliente;
import utp.poo.proyecto.entities.personas.Vendedor;
import utp.poo.proyecto.entities.productos.Producto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// MEJORADO: La clase ahora es más genérica.
public class ComprobanteUtils {

    // MEJORADO: El método ahora recibe el TIPO de comprobante y se adapta.
    public static String imprimirComprobante(String tipoComprobante, Vendedor vendedor, Cliente cliente, List<Producto> productos) {
        double totalGeneral = 0;
        for (Producto p : productos) {
            if (p != null && p.getPrecioVenta() > 0 && p.getCantidad() > 0) {
                totalGeneral += p.getPrecioVenta() * p.getCantidad();
            }
        }

        double subtotal = totalGeneral / 1.18;
        double igv = totalGeneral - subtotal;
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        StringBuilder comprobante = new StringBuilder();
        comprobante.append("=========================================\n");
        comprobante.append("              CAFÉ AGUSTO\n");
        comprobante.append("=========================================\n");
        
        // La lógica clave está aquí:
        if ("FACTURA".equalsIgnoreCase(tipoComprobante)) {
            comprobante.append("Factura de Venta Electrónica\n");
        } else {
            comprobante.append("Boleta de Venta Electrónica\n");
        }

        comprobante.append(String.format("Fecha: %s\n", fecha));
        comprobante.append(String.format("Vendedor: %s\n", vendedor.getNombre()));
        comprobante.append("-----------------------------------------\n");
        
        if ("FACTURA".equalsIgnoreCase(tipoComprobante)) {
            comprobante.append(String.format("Cliente: %s\n", cliente.getRazonSocial()));
            comprobante.append(String.format("RUC: %s\n", cliente.getRuc()));
        } else {
            comprobante.append(String.format("Cliente: %s\n", cliente.getNombre() != null && !cliente.getNombre().isEmpty() ? cliente.getNombre() : "Varios"));
            comprobante.append(String.format("DNI: %s\n", cliente.getDni() != null ? cliente.getDni().toString() : "S/D"));
        }

        comprobante.append("-----------------------------------------\n");
        comprobante.append(String.format("%-18s %4s %9s %9s\n", "Producto", "Cant", "P.Unit", "Importe"));
        comprobante.append("-----------------------------------------\n");

        for (Producto p : productos) {
             if (p != null) {
                double importe = p.getPrecioVenta() * p.getCantidad();
                comprobante.append(String.format("%-18.18s %4d S/ %7.2f S/ %7.2f\n",
                        p.getNombre(), p.getCantidad(), p.getPrecioVenta(), importe));
            }
        }
        
        comprobante.append("-----------------------------------------\n");
        comprobante.append(String.format("SUBTOTAL:              S/ %10.2f\n", subtotal));
        comprobante.append(String.format("IGV (18%%):             S/ %10.2f\n", igv));
        comprobante.append(String.format("TOTAL A PAGAR:         S/ %10.2f\n", totalGeneral));
        comprobante.append("=========================================\n\n");
        
        return comprobante.toString();
    }
}