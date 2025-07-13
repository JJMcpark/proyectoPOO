package utp.poo.proyecto.services;

import utp.poo.proyecto.entities.personas.Cliente;
import utp.poo.proyecto.entities.personas.Vendedor;
import utp.poo.proyecto.entities.productos.Producto;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public interface ComprobanteService {
    void guardarBoleta(Vendedor vendedor, Cliente cliente, List<Producto> productos);
    void mostrarBoletas();
    void generarBoleta(Scanner sc);
    Producto crearProductoInteractivo(Scanner sc);

    // NUEVO: Métodos para la factura
    void generarFactura(Scanner sc);
    void mostrarFacturas();
}
