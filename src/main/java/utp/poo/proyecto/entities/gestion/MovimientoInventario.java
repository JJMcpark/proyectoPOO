package utp.poo.proyecto.entities.gestion;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;

import utp.poo.proyecto.entities.productos.Producto;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Producto producto;
    private String tipo; // ENTRADA, SALIDA, MERMA
    private int cantidad;
    private LocalDateTime fecha;
    private String observacion;
    private double precioUnitario;
    
    public MovimientoInventario(Producto producto, String tipo, int cantidad, 
                               String observacion, double precioUnitario) {
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.observacion = observacion;
        this.precioUnitario = precioUnitario;
    }

    //getters y setters reemplazados por lombok 
    
    public double getTotal() {
        return cantidad * precioUnitario;
    }
    
    @Override
    public String toString() {
        return String.format("%s | %d | %s | $%.2f | $%.2f", 
            tipo, cantidad, fecha, precioUnitario, getTotal());
    }
}