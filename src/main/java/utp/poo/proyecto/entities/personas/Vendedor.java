package utp.poo.proyecto.entities.personas;

import lombok.*;
import utp.poo.proyecto.entities.productos.Producto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vendedor extends Persona {

    private Long id;
    private String correo;
    private Long telefono;

    public void vender(Producto producto) {
        System.out.println("Vendiendo producto: " + producto.getNombre());
    }
    
    public void mostrarInfo() {
        System.out.println("Vendedor: " + getNombre() + ", DNI: " + getDni());
    }

    public void generarFactura(){
        System.out.println("El vendedor " + getNombre() + " esta generando una factura");
    }
    
    public void autorizarDescuento(){
        System.out.println("Se autoriza el descuento");
    }

}
