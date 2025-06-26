package utp.poo.proyecto.entities.productos;

import lombok.*;

@Getter
@Setter
public class Comida extends Producto {
    private boolean paraLlevar;
    private String tipo;

    public Comida(Long idProducto, String nombre, String descripcion, String categoria,
                  double precioVenta, double descuento, boolean paraLlevar, String tipo, int cantidad) {
        super(idProducto, nombre, descripcion, categoria, precioVenta, descuento, cantidad);
        this.paraLlevar = paraLlevar;
        this.tipo = tipo;
    }

    public Comida() {
        super();
    }

    public void mostrarTipo() {
        System.out.println("Tipo de comida: " + tipo + (paraLlevar ? " (Para llevar)" : " (Para consumir aquí)"));
    }
}
