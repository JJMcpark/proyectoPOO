package utp.poo.proyecto.entities.personas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Persona {
    
    private String nombre;
    private long dni;
    
    public Persona(String nombre, long dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    
    public abstract void mostrarInfo();
    
}
