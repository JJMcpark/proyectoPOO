package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;

import utp.poo.proyecto.entities.productos.Producto;

@Service
public interface KardexService {

    void registrarMovimientoInventario(String tipo, Producto producto, int cantidad, String observacion, double precioUnitario);

    void mostrarKardex();
}
