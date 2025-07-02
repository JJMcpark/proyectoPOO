package utp.poo.proyecto.services;

import java.util.Scanner;
import utp.poo.proyecto.entities.productos.Producto;
import org.springframework.stereotype.Service;

@Service
public interface KardexService {
    void registrarMovimientoInventario(String tipo, Producto producto, Integer cantidad, String observacion, Double precioUnitario);
    void mostrarKardex();
    void registrarMovimientoInventarioInteractivo(Scanner sc);
}
