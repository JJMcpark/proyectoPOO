package utp.poo.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utp.poo.proyecto.services.*;
import utp.poo.proyecto.entities.productos.*;
import utp.poo.proyecto.entities.personas.*;
import utp.poo.proyecto.entities.gestion.*;
import utp.poo.proyecto.utils.FileUtils;
import utp.poo.proyecto.utils.BoletaUtils;
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
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    // Pedir datos y registrar movimiento
                    System.out.print("Tipo de movimiento (ENTRADA/SALIDA): ");
                    String tipo = sc.nextLine();
                    // Aquí deberías pedir los datos del producto, cantidad, observación, precioUnitario
                    // Por ejemplo:
                    // Producto producto = ...;
                    // int cantidad = ...;
                    // String observacion = ...;
                    // double precioUnitario = ...;
                    // kardexService.registrarMovimientoInventario(tipo, producto, cantidad, observacion, precioUnitario);
                    break;
                case 2:
                    kardexService.mostrarKardex();
                    break;
                case 3:
                
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        System.out.println("=======================================");
        System.out.println("      ☕ ¡Bienvenido a Café Agusto! ☕");
        System.out.println("  Disfruta la mejor experiencia de café");
        System.out.println("=======================================\n");

        Vendedor vendedor = new Vendedor();
        System.out.print("Ingrese el nombre del vendedor: ");
        vendedor.setNombre(sc.nextLine());
        Cliente cliente = new Cliente();
        System.out.print("Ingrese el nombre del cliente: ");
        cliente.setNombre(sc.nextLine());
        System.out.print("Ingrese el DNI del cliente: ");
        cliente.setDni(sc.nextLong());
        sc.nextLine();

        List<Producto> productos = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            System.out.print("Ingrese el tipo de producto (Bebida/Comida): ");
            String tipoProducto = sc.nextLine();

            if (tipoProducto.equalsIgnoreCase("Bebida")) {
                Bebida bebida = new Bebida();
                System.out.print("Nombre de la bebida: ");
                bebida.setNombre(sc.nextLine());
                System.out.print("Cantidad: ");
                bebida.setCantidad(sc.nextInt());
                sc.nextLine();
                System.out.print("Tamaño (Pequeña/Mediana/Grande): ");
                bebida.setTamaño(sc.nextLine());
                System.out.print("Precio unitario: ");
                bebida.setPrecioVenta(sc.nextDouble());
                sc.nextLine();
                productos.add(bebida);
            } else if (tipoProducto.equalsIgnoreCase("Comida")) {
                Comida comida = new Comida();
                System.out.print("Nombre de la comida: ");
                comida.setNombre(sc.nextLine());
                System.out.print("¿Es para llevar? (si/no): ");
                comida.setParaLlevar(sc.nextLine().equalsIgnoreCase("si"));
                System.out.print("Precio unitario: ");
                comida.setPrecioVenta(sc.nextDouble());
                System.out.print("Cantidad: ");
                comida.setCantidad(sc.nextInt());
                sc.nextLine();
                productos.add(comida);
            } else {
                System.out.println("Tipo de producto no reconocido.");
            }

            System.out.print("¿Desea agregar otro producto? (si/no): ");
            agregarMas = sc.nextLine().equalsIgnoreCase("si");
        }

        // calcular total
        double subtotal = 0;
        for (Producto p : productos) {
            subtotal += p.getPrecioVenta() * p.getCantidad();
        }
        double igv = subtotal * 0.18;
        double total = subtotal + igv;

        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        System.out.println("\n=======================================");
        System.out.println("         ☕ CAFÉ AGUSTO BOLETA ☕");
        System.out.println("         ┌───────────────┐");
        System.out.println("         │   (  )   (   )│");
        System.out.println("         │    )  (   )  (│");
        System.out.println("         │   (   )  (   )│");
        System.out.println("         └───────────────┘");
        System.out.println("=======================================");
        System.out.println("Fecha: " + fecha);
        System.out.println("Vendedor: " + vendedor.getNombre());
        System.out.println("Cliente: " + cliente.getNombre() + " | DNI: " + cliente.getDni());
        System.out.println("Productos:");
        for (Producto p : productos) {
            System.out.println("- " + p.getNombre() + " x" + p.getCantidad() + " @ S/ " + p.getPrecioVenta() + " = S/ " + (p.getPrecioVenta() * p.getCantidad()));
        }
        System.out.println("---------------------------------------");
        System.out.printf("SUBTOTAL: S/ %.2f\n", subtotal);
        System.out.printf("IGV (18%%): S/ %.2f\n", igv);
        System.out.printf("TOTAL: S/ %.2f\n", total);
        System.out.println("=======================================");
        System.out.println("   ¡Gracias por tu compra en Café Agusto!");
        System.out.println("=======================================");

        String boleta = BoletaUtils.imprimirBoleta(vendedor, cliente, productos);
        FileUtils.guardarTexto("src/main/resources/boletas/boletas.txt", boleta, true);
        System.out.println(boleta);
        comprobanteService.guardarBoleta(vendedor, cliente, productos);
        //sc.close();
    }
}
