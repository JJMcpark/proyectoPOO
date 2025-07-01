package utp.poo.proyecto.entities.gestion;

import lombok.*;
import utp.poo.proyecto.entities.personas.Cliente;
import utp.poo.proyecto.entities.personas.Vendedor;
import utp.poo.proyecto.entities.productos.Producto;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Comprobante {
    protected Long id;
    protected Cliente cliente;
    protected Vendedor vendedor;
    protected String fechaEmision;
    protected List<Producto> productos;
    protected double total;

    public abstract double calcularTotal();
}
