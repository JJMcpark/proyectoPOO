package utp.poo.proyecto.entities;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Proveedor {

    private Long id;
    private String nombre;
    private String correo;
    private Long telefono;

    public Proveedor(Long id, String nombre, String correo, Long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    public Proveedor(Long id, String nombre, String correo, Long telefono, List<Inventario> inventarios) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    public Proveedor(Long id, String nombre, String correo, Long telefono, List<Inventario> inventarios, List<Factura> facturas) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombre() {
    return nombre;
    }

    public void asignarProducto(Producto producto) {
    System.out.println("Proveedor " + this.nombre + " ha sido asignado al producto: " + producto.getNombre());
    }
    
    public void metodoPago(){
        
        System.out.println("Los metodos de pago disponibles son: ");
        
    }
}
