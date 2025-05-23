package utp.poo.proyecto.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Cliente {
    
    private Long id;
    private String nombre;
    private String correo;
    private Long telefono;

    public Cliente(Long id, String nombre, String correo, Long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public void realizarPedidos() {
        System.out.println(nombre + " está realizando un pedido.");
        
    }

    public void actualizarDatos(String nuevoNombre, String nuevoCorreo, Long nuevoTelefono) {
        this.nombre = nuevoNombre;
        this.correo = nuevoCorreo;
        this.telefono = nuevoTelefono;
        System.out.println("Datos del cliente actualizados.");
    }
    
    public String getNombre(){
        
        return nombre;
        
    }
    
    public String getCorreo(){
        
        return correo;
        
    }
    
    public long getTelefono(){
        
        return telefono;
        
    }
    
    public long getId(){
        
        return id;
        
    }

}
