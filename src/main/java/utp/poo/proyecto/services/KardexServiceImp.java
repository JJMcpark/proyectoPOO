package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

import utp.poo.proyecto.entities.gestion.MovimientoInventario;
import utp.poo.proyecto.entities.productos.Producto;

@Data
@Service
public class KardexServiceImp implements KardexService {

    private List<MovimientoInventario> movimientos = new ArrayList<>();

    @Override
    public void registrarMovimientoInventario(String tipo, Producto producto, int cantidad, String observacion, double precioUnitario) {
        MovimientoInventario mov = new MovimientoInventario(producto, tipo, cantidad, observacion, precioUnitario);
        movimientos.add(mov);
        // Opcional: guardar en archivo
    }

    public void mostrarKardex() {
        for (MovimientoInventario mov : movimientos) {
            System.out.println(mov);
        }
    }

}
