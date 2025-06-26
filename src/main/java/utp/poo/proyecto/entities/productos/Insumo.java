package utp.poo.proyecto.entities.productos;

import lombok.*;

@Getter
@Setter
public class Insumo extends Producto {
    private String unidadMedida;
    private double cantidadMinima;
    
    public Insumo(Long idProducto, String nombre, String descripcion, String categoria, 
                  double precioVenta, double descuento, String unidadMedida, double cantidadMinima, int cantidad) {
        super(idProducto, nombre, descripcion, categoria, precioVenta, descuento, cantidad);
        this.unidadMedida = unidadMedida;
        this.cantidadMinima = cantidadMinima;
    }
}
