package utp.poo.proyecto.entities.personas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona {
    
    private String nombre;
    private Long dni;
    
    public abstract void mostrarInfo();


    
}
