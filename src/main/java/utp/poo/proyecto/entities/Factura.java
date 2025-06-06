package utp.poo.proyecto.entities;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Factura {

    private Long idFactura;
    private Cliente cliente;
    private Vendedor vendedor;
    private String fechaEmision;
    private List<Producto> productos;
    private double totalFactura;

    public Factura(Long idFactura, Cliente cliente, Vendedor vendedor, String fechaEmision, double totalFactura) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fechaEmision = fechaEmision;
        this.totalFactura = totalFactura;
    }

    public Factura calcularIGV(double totalFactura) {
        double igv = totalFactura * 0.18;
        this.totalFactura = totalFactura + igv;
        return this;
    }

}
