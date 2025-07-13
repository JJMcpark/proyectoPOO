package utp.poo.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;
import utp.poo.proyecto.services.KardexService;
import utp.poo.proyecto.services.ComprobanteService;
import utp.poo.proyecto.utils.ConsoleReader;

@Component
public class ConsoleMenu implements CommandLineRunner {
    @Autowired
    private KardexService kardexService;

    @Autowired
    private ComprobanteService comprobanteService;

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("=== CAFÉ AGUSTO - MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar Venta (Generar Boleta)");
            System.out.println("2. Ver Boletas de Venta");
            System.out.println("3. Registrar Movimiento de Inventario (Kardex)");
            System.out.println("4. Ver Kardex");
            System.out.println("5. Generar Factura");
            System.out.println("6. Ver Facturas");
            System.out.println("0. Salir");

            // MEJORADO: Se usa el nuevo método para leer opciones de forma segura.
            int opcion = ConsoleReader.leerOpcion(sc, "Seleccione una opción: ", 0, 6);

            switch (opcion) {
                case 0:
                    salir = true;
                    System.out.println("Saliendo del sistema. ¡Gracias por usar Café A gusto!");
                    break;
                case 1:
                    comprobanteService.generarBoleta(sc);
                    break;
                case 2:
                    comprobanteService.mostrarBoletas();
                    break;
                case 3:
                    kardexService.registrarMovimientoInventarioInteractivo(sc);
                    break;
                case 4:
                    kardexService.mostrarKardex();
                    break;
                case 5:
                    comprobanteService.generarFactura(sc);
                    break;
                case 6:
                    comprobanteService.mostrarFacturas();
                    break;
            }
        }
        sc.close();
    }
}