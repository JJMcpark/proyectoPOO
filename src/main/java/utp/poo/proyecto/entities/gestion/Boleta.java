package utp.poo.proyecto.entities.gestion;

import lombok.*;
import utp.poo.proyecto.entities.personas.Cliente;
import utp.poo.proyecto.entities.personas.Vendedor;
import utp.poo.proyecto.entities.productos.Producto;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Boleta extends Comprobante {

    public Boleta(Long id, Cliente cliente, Vendedor vendedor, String fechaEmision, List<Producto> productos) {
        super(id, cliente, vendedor, fechaEmision, productos, 0);
        this.total = calcularTotal();
    }

    @Override
    public double calcularTotal() {
        double subtotal = 0;
        for (Producto p : productos) {
            subtotal += p.getPrecioVenta() * p.getCantidad();
        }
        double igv = subtotal * 0.18;
        return subtotal + igv;
    }
}
