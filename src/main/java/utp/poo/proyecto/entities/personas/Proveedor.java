package utp.poo.proyecto.entities.personas;

import lombok.*;
import utp.poo.proyecto.entities.gestion.Factura;
import utp.poo.proyecto.entities.productos.Producto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    private Long id;
    private String nombre;
    private String correo;
    private Long telefono;
    private List<Producto> productos;
    private List<Factura> facturas;

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
