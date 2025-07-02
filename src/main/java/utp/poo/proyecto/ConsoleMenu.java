package utp.poo.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;
import utp.poo.proyecto.services.KardexService;
import utp.poo.proyecto.services.ComprobanteService;

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
            System.out.println("=== CAFÉ AGUSTO ===");
            System.out.println("1. Registrar movimiento de inventario");
            System.out.println("2. Ver Kardex");
            System.out.println("3. Registrar venta");
            System.out.println("4. Ver boletas");
            System.out.println("5. Salir");

            int opcion = -1;
            while (opcion < 1 || opcion > 5) {
                System.out.print("Seleccione una opción: ");
                String opcionStr = sc.nextLine().trim();
                opcion = parseOpcion(opcionStr);
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            }

            switch (opcion) {
                case 1:
                    kardexService.registrarMovimientoInventarioInteractivo(sc);
                    break;
                case 2:
                    kardexService.mostrarKardex();
                    break;
                case 3:
                    comprobanteService.generarBoleta(sc);
                    break;
                case 4:
                    comprobanteService.mostrarBoletas();
                    break;
                case 5:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
            }
        }
        sc.close();
    }

    private int parseOpcion(String opcionStr) {
        try {
            return Integer.parseInt(opcionStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
