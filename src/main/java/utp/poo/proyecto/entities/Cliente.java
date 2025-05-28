package utp.poo.proyecto.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
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

    // Constructor solo con id, nombre y telefono (opcional)
    public Cliente(Long id, String nombre, Long telefono) {
        super(nombre, id);
        this.id = id;
        this.telefono = telefono;
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

    // Métodos de acceso ya estan implementados por Lombok
    public String getNombre(){
        return super.getNombre();
    }
    public String getCorreo() {
        return correo;
    }
    public long getDni() {
        return super.getDni();
    }
    public long getTelefono(){
        return telefono;
    }
    public long getId(){
        return id;
    }

}
