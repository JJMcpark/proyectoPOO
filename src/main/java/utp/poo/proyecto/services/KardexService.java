package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;

@Service
public interface KardexService {

    void registrarMovimientoInventario(String tipo, int cantidad, String observacion, double precioUnitario);

    void mostrarKardex();

    void mostrarKardexPorProducto(String nombreProducto);

    void mostrarKardexPorFecha(String fechaInicio, String fechaFin);

    void mostrarKardexPorTipo(String tipoMovimiento);

}
