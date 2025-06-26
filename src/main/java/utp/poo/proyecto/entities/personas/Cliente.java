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

    public Cliente(Long id, String nombre, Long dni, String correo, Long telefono) {
        super(nombre, dni);
        this.id = id;
        this.telefono = telefono;
        this.correo = correo;
    }

    public void realizarPedidos() {
        System.out.println(getNombre() + " está realizando un pedido.");

    }

    public void actualizarDatos(String nuevoNombre, Long nuevoTelefono) {
        setNombre(nuevoNombre);
        setTelefono(nuevoTelefono);
        System.out.println("Datos del cliente actualizados.");
    }

    // Nuevo método para mostrar info como en el cliente pequeño
    @Override
    public void mostrarInfo() {
        System.out.println("Datos del cliente: ");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Numero de dni: " + getDni());
        System.out.println("Numero de telefono: " + getTelefono());
    }
}
