package utp.poo.proyecto.entities.personas;

import lombok.*;
import utp.poo.proyecto.entities.productos.Producto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vendedor {

    private Long id;
    private String nombre;
    private String correo;
    private Long telefono;

    public void vender(Producto producto) {
        System.out.println("Vendiendo producto: " + producto.getNombre());
    }
    
    public void generarFactura(){
        System.out.println("El vendedor" + nombre + " esta generando una factura");
    }
    
    public void autorizarDescuento(){
        System.out.println("Se autoriza el descuento");
    }

}
