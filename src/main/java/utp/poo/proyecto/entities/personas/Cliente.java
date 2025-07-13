package utp.poo.proyecto.entities.personas;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Persona {
    
    private Long id;
    private Long telefono;
    private String correo;

    // NUEVO: Campos necesarios para la Factura.
    private String ruc;
    private String razonSocial;

    public Cliente(Long id, String nombre, Long dni, String correo, Long telefono) {
        super(nombre, dni);
        this.id = id;
        this.telefono = telefono;
        this.correo = correo;
    }

    // ... (El resto de la clase no cambia)
    public void realizarPedidos() {
        System.out.println(getNombre() + " está realizando un pedido.");

    }

    public void actualizarDatos(String nuevoNombre, Long nuevoTelefono) {
        setNombre(nuevoNombre);
        setTelefono(nuevoTelefono);
        System.out.println("Datos del cliente actualizados.");
    }
    
    @Override
    public void mostrarInfo() {
        System.out.println("Datos del cliente: ");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Numero de dni: " + getDni());
        System.out.println("Numero de telefono: " + getTelefono());
    }
}