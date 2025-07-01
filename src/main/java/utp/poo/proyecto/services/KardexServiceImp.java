package utp.poo.proyecto.services;

import org.springframework.stereotype.Service;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

import utp.poo.proyecto.entities.gestion.MovimientoInventario;
import utp.poo.proyecto.entities.productos.Producto;
import utp.poo.proyecto.utils.FileUtils;

@Data
@Service
public class KardexServiceImp implements KardexService {

    private List<MovimientoInventario> movimientos = new ArrayList<>();

    @Override
    public void registrarMovimientoInventario(String tipo, Producto producto, int cantidad, String observacion, double precioUnitario) {
        MovimientoInventario mov = new MovimientoInventario(producto, tipo, cantidad, observacion, precioUnitario);
        movimientos.add(mov);
        String registro = mov.toString();
        FileUtils.guardarTexto("src/main/resources/kardex/kardex.txt", registro, true);
    }

    @Override
    public void mostrarKardex() {
        List<String> registros = FileUtils.leerLineas("src/main/resources/kardex/kardex.txt");
        for (String linea : registros) {
            System.out.println(linea);
        }
    }

}
