package utp.poo.proyecto.services;

import utp.poo.proyecto.entities.personas.*;
import utp.poo.proyecto.entities.productos.*;
import utp.poo.proyecto.utils.BoletaUtils;
import utp.poo.proyecto.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ComprobanteServiceImp implements ComprobanteService {

    private static final String RUTA_BOLETAS = "src/main/resources/boletas/boletas.txt";
    private final Vendedor vendedorPorDefecto;

    public ComprobanteServiceImp() {
        Random random = new Random();
        vendedorPorDefecto = new Vendedor();
        vendedorPorDefecto.setNombre("Josue");
        vendedorPorDefecto.setDni(Math.abs(random.nextLong() % 100000000));
        vendedorPorDefecto.setCorreo("josue" + random.nextInt(1000) + "@cafe.com");
        vendedorPorDefecto.setTelefono(900000000L + random.nextInt(99999999));
    }

    @Override
    public void generarBoleta(Scanner sc) {
        System.out.println("=======================================");
        System.out.println("      ☕ ¡Bienvenido a Café Agusto! ☕");
        System.out.println("  Disfruta la mejor experiencia de café");
        System.out.println("=======================================\n");

        Vendedor vendedor = vendedorPorDefecto;

        Cliente cliente = new Cliente();
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = sc.nextLine().trim();
        cliente.setNombre(nombreCliente.isEmpty() ? null : nombreCliente);

        System.out.print("Ingrese el DNI del cliente: ");
        String dniStr = sc.nextLine().trim();
        if (dniStr.isEmpty()) {
            cliente.setDni(null);
        } else {
            try {
                cliente.setDni(Long.parseLong(dniStr));
            } catch (NumberFormatException e) {
                cliente.setDni(null);
            }
        }

        List<Producto> productos = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            Producto producto = crearProductoInteractivo(sc);
            if (producto != null) {
                productos.add(producto);
            }
            System.out.print("¿Desea agregar otro producto? (si/no): ");
            agregarMas = sc.nextLine().trim().equalsIgnoreCase("si");
        }

        String boleta = BoletaUtils.imprimirBoleta(vendedor, cliente, productos);
        FileUtils.guardarTexto(RUTA_BOLETAS, boleta, true);

        System.out.println("\n=== BOLETA GENERADA ===");
        System.out.println(boleta);
        System.out.println("========================\n");
    }

    @Override
    public Producto crearProductoInteractivo(Scanner sc) {
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Insumo");
        System.out.println("2. Comida");
        System.out.println("3. Bebida");
        System.out.print("Opción: ");
        int tipo = 0;
        while (tipo < 1 || tipo > 3) {
            String tipoStr = sc.nextLine().trim();
            try {
                tipo = Integer.parseInt(tipoStr);
                if (tipo < 1 || tipo > 3) {
                    System.out.print("Opción inválida. Intente de nuevo: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Ingrese una opción válida (1-3): ");
            }
        }

        Producto producto = null;
        switch (tipo) {
            case 1:
                producto = new Insumo();
                System.out.print("Nombre del insumo: ");
                String nombreInsumo = sc.nextLine().trim();
                producto.setNombre(nombreInsumo.isEmpty() ? null : nombreInsumo);
                System.out.print("Cantidad: ");
                producto.setCantidad(leerEnteroPositivoNull(sc));
                System.out.print("Precio unitario: ");
                producto.setPrecioVenta(leerDoublePositivoNull(sc));
                break;
            case 2:
                producto = new Comida();
                System.out.print("Nombre de la comida: ");
                String nombreComida = sc.nextLine().trim();
                producto.setNombre(nombreComida.isEmpty() ? null : nombreComida);
                System.out.print("¿Es para llevar? (si/no): ");
                String paraLlevar = sc.nextLine().trim();
                ((Comida) producto).setParaLlevar(paraLlevar.isEmpty() ? null : paraLlevar.equalsIgnoreCase("si"));
                System.out.print("Precio unitario: ");
                producto.setPrecioVenta(leerDoublePositivoNull(sc));
                System.out.print("Cantidad: ");
                producto.setCantidad(leerEnteroPositivoNull(sc));
                break;
            case 3:
                producto = new Bebida();
                System.out.print("Nombre de la bebida: ");
                String nombreBebida = sc.nextLine().trim();
                producto.setNombre(nombreBebida.isEmpty() ? null : nombreBebida);
                System.out.print("Cantidad: ");
                producto.setCantidad(leerEnteroPositivoNull(sc));
                System.out.print("Tamaño (Pequeña/Mediana/Grande): ");
                String tam = sc.nextLine().trim();
                ((Bebida) producto).setTamaño(tam.isEmpty() ? null : tam);
                System.out.print("Precio unitario: ");
                producto.setPrecioVenta(leerDoublePositivoNull(sc));
                break;
        }
        return producto;
    }

    // Métodos auxiliares para validar entrada de números (aceptan null)
    private Integer leerEnteroPositivoNull(Scanner sc) {
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return null;
        try {
            int valor = Integer.parseInt(entrada);
            return valor >= 0 ? valor : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double leerDoublePositivoNull(Scanner sc) {
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return null;
        try {
            double valor = Double.parseDouble(entrada);
            return valor >= 0 ? valor : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void guardarBoleta(Vendedor vendedor, Cliente cliente, List<Producto> productos) {
        String boleta = BoletaUtils.imprimirBoleta(vendedor, cliente, productos);
        FileUtils.guardarTexto(RUTA_BOLETAS, boleta, true);
        System.out.println("Boleta guardada correctamente.");
    }

    @Override
    public void mostrarBoletas() {
        List<String> boletas = FileUtils.leerLineas(RUTA_BOLETAS);
        if (boletas.isEmpty()) {
            System.out.println("No hay boletas registradas.");
        } else {
            System.out.println("=== BOLETAS REGISTRADAS ===");
            for (String linea : boletas) {
                System.out.println(linea);
            }
            System.out.println("===========================");
        }
    }
}
