package utp.poo.proyecto.entities;

import lombok.*;

@Getter
@Setter
public class Bebida extends Producto {
    private String tamaño;
    private boolean caliente;
    
    public Bebida(Long idProducto, String nombre, String descripcion, String categoria, 
                 double precioVenta, double descuento, String tamaño, boolean caliente, int cantidad) {
        super(idProducto, nombre, descripcion, categoria, precioVenta, descuento, cantidad);
        this.tamaño = tamaño;
        this.caliente = caliente;
    }

    public Bebida() {
        super();
    }

    public void darDescuento() {
        System.out.println("La bebida " + getNombre() + " tiene un descuento del " + 
                          getDescuento() + "% por promoción del día");
    }
    
    public void prepararBebida() {
        System.out.println("Preparando bebida " + getNombre() + 
                          (caliente ? " caliente" : " fría") + 
                          " en tamaño " + tamaño);
    }
}
