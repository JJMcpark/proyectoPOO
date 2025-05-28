package utp.poo.proyecto.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class Producto {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioVenta;
    private double descuento;
    private int cantidad;   

    Producto(Long idProducto, String nombre, String descripcion, String categoria, double precioVenta, double descuento, int cantidad){

    this.idProducto = idProducto;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.precioVenta = precioVenta;
    this.descuento = descuento;
    this.cantidad = cantidad;
    }

    public long getIdProducto(){
        return idProducto;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecioVenta() {
        return precioVenta;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void darDescuento(){

        System.out.println("El descuento para el producto" + nombre + "es de: " + descuento);
        
    }
    
    
    public void aplicarDescuento(){
        
        precioVenta = precioVenta - (precioVenta * descuento / 100);
        System.out.println("El precio con el descuento aplicado es: " + precioVenta);
    } 
}
