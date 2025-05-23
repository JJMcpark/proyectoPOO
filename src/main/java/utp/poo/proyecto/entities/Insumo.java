package utp.poo.proyecto.entities;

import lombok.*;

@Getter
@Setter
public class Insumo extends Producto {
    private String unidadMedida;
    private double cantidadMinima;
    
    public Insumo(Long idProducto, String nombre, String descripcion, String categoria, 
                  double precioVenta, double descuento, String unidadMedida, double cantidadMinima) {
        super(idProducto, nombre, descripcion, categoria, precioVenta, descuento);
        this.unidadMedida = unidadMedida;
        this.cantidadMinima = cantidadMinima;
    }
    
    @Override
    public void darDescuento() {
        System.out.println("El insumo " + getNombre() + " tiene un descuento del " + 
                           getDescuento() + "% por compra al mayoreo");
    }
}
