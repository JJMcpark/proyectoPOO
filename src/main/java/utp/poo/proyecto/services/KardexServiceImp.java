package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;
import lombok.*;

@Data
@Service
public class KardexServiceImp implements KardexService {

    @Override
    public void registrarMovimientoInventario(String tipo, int cantidad, String observacion, double precioUnitario) {
    }

    @Override
    public void mostrarKardex() {
    }

    @Override
    public void mostrarKardexPorProducto(String nombreProducto) {
    }

    @Override
    public void mostrarKardexPorFecha(String fechaInicio, String fechaFin) {
    }

    @Override
    public void mostrarKardexPorTipo(String tipoMovimiento) {
    }

}
