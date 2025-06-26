package utp.poo.proyecto.entities.productos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Producto {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioVenta;
    private int cantidad;
    private double descuento;   

    Producto(Long idProducto, String nombre, String descripcion, String categoria, double precioVenta, double descuento, int cantidad){

    this.idProducto = idProducto;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.precioVenta = precioVenta;
    this.cantidad = cantidad;
    }
    
    public void aplicarDescuento() {
        precioVenta = precioVenta - (precioVenta * descuento / 100);
        System.out.println("El precio con el descuento aplicado es: " + precioVenta);
    } 
}
