package utp.poo.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;
import utp.poo.proyecto.services.KardexService;
import utp.poo.proyecto.services.ComprobanteService;
import utp.poo.proyecto.utils.ConsoleReader; // MEJORADO: Se importa la nueva utilidad

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
            System.out.println("5. Salir");

            // MEJORADO: Se usa el nuevo método para leer opciones de forma segura.
            int opcion = ConsoleReader.leerOpcion(sc, "Seleccione una opción: ", 1, 5);

            switch (opcion) {
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
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema Café Agusto! ¡Hasta luego!");
                    break;
            }
        }
        sc.close();
    }
}