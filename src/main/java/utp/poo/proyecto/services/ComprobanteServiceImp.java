package utp.poo.proyecto.services;

import utp.poo.proyecto.entities.personas.*;
import utp.poo.proyecto.entities.productos.*;
import utp.poo.proyecto.utils.ComprobanteUtils; // CORRECCIÓN: Usar la clase renombrada
import utp.poo.proyecto.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteServiceImp implements ComprobanteService {

    private static final String RUTA_BOLETAS = "data/boletas/boletas.txt";
    private static final String RUTA_FACTURAS = "data/facturas/facturas.txt";

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
        System.out.println("\n---[ GENERACIÓN DE BOLETA ]---");
        
        Cliente cliente = new Cliente();
        System.out.print("Ingrese el nombre del cliente (opcional): ");
        cliente.setNombre(sc.nextLine().trim());
        System.out.print("Ingrese el DNI del cliente (opcional): ");
        String dniStr = sc.nextLine().trim();
        if (!dniStr.isEmpty()) {
            try {
                cliente.setDni(Long.parseLong(dniStr));
            } catch (NumberFormatException e) {
                System.out.println("DNI no válido, se dejará en blanco.");
            }
        }
        
        List<Producto> productos = agregarProductos(sc);
        if (productos.isEmpty()) {
            System.out.println("Venta cancelada: no se agregaron productos.");
            return;
        }

        // CORRECCIÓN CLAVE: Se usa 'ComprobanteUtils' en lugar de 'BoletaUtils'.
        String boleta = ComprobanteUtils.imprimirComprobante("BOLETA", vendedorPorDefecto, cliente, productos);
        FileUtils.guardarTexto(RUTA_BOLETAS, boleta, true);

        System.out.println("\n=== BOLETA GENERADA Y GUARDADA ===");
        System.out.println(boleta);
    }

    @Override
    public void generarFactura(Scanner sc) {
        System.out.println("\n---[ GENERACIÓN DE FACTURA ]---");

        Cliente cliente = new Cliente();
        System.out.print("Ingrese el RUC del cliente: ");
        cliente.setRuc(sc.nextLine().trim());
        System.out.print("Ingrese la Razón Social del cliente: ");
        cliente.setRazonSocial(sc.nextLine().trim());
        
        List<Producto> productos = agregarProductos(sc);
        if (productos.isEmpty()) {
            System.out.println("Venta cancelada: no se agregaron productos.");
            return;
        }

        String factura = ComprobanteUtils.imprimirComprobante("FACTURA", vendedorPorDefecto, cliente, productos);
        FileUtils.guardarTexto(RUTA_FACTURAS, factura, true);

        System.out.println("\n=== FACTURA GENERADA Y GUARDADA ===");
        System.out.println(factura);
    }

    private List<Producto> agregarProductos(Scanner sc) {
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
        return productos;
    }

    @Override
    public Producto crearProductoInteractivo(Scanner sc) {
        // Esta lógica de creación de producto se mantiene como estaba en tu código original.
        // La haremos más robusta en el Paso 3.
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Insumo");
        System.out.println("2. Comida");
        System.out.println("3. Bebida");
        System.out.print("Opción: ");
        String tipoStr = sc.nextLine().trim();
        int tipo = Integer.parseInt(tipoStr); // Esto puede fallar, lo arreglaremos en el Paso 3.

        Producto producto = null;
        switch (tipo) {
            case 1:
                producto = new Insumo();
                System.out.print("Nombre del insumo: ");
                producto.setNombre(sc.nextLine().trim());
                System.out.print("Cantidad: ");
                producto.setCantidad(Integer.parseInt(sc.nextLine().trim()));
                System.out.print("Precio unitario (IGV incluido): ");
                producto.setPrecioVenta(Double.parseDouble(sc.nextLine().trim()));
                break;
            case 2:
                producto = new Comida();
                System.out.print("Nombre de la comida: ");
                producto.setNombre(sc.nextLine().trim());
                System.out.print("¿Es para llevar? (si/no): ");
                ((Comida) producto).setParaLlevar(sc.nextLine().trim().equalsIgnoreCase("si"));
                System.out.print("Precio unitario (IGV incluido): ");
                producto.setPrecioVenta(Double.parseDouble(sc.nextLine().trim()));
                System.out.print("Cantidad: ");
                producto.setCantidad(Integer.parseInt(sc.nextLine().trim()));
                break;
            case 3:
                producto = new Bebida();
                System.out.print("Nombre de la bebida: ");
                producto.setNombre(sc.nextLine().trim());
                System.out.print("Cantidad: ");
                producto.setCantidad(Integer.parseInt(sc.nextLine().trim()));
                System.out.print("Tamaño (Pequeña/Mediana/Grande): ");
                ((Bebida) producto).setTamaño(sc.nextLine().trim());
                System.out.print("Precio unitario (IGV incluido): ");
                producto.setPrecioVenta(Double.parseDouble(sc.nextLine().trim()));
                break;
        }
        return producto;
    }

    // Este método ya no es necesario aquí, lo eliminamos para evitar confusión.
    // Lo crearemos en una clase aparte en el Paso 3.
    // private Integer leerEnteroPositivoNull(Scanner sc) { ... }
    // private Double leerDoublePositivoNull(Scanner sc) { ... }

    @Override
    public void guardarBoleta(Vendedor vendedor, Cliente cliente, List<Producto> productos) {
        String boleta = ComprobanteUtils.imprimirComprobante("BOLETA", vendedor, cliente, productos);
        FileUtils.guardarTexto(RUTA_BOLETAS, boleta, true);
        System.out.println("Boleta guardada correctamente.");
    }

    @Override
    public void mostrarBoletas() {
        System.out.println("\n---[ BOLETAS REGISTRADAS ]---");
        List<String> boletas = FileUtils.leerLineas(RUTA_BOLETAS);
        if (boletas.isEmpty()) {
            System.out.println("No hay boletas registradas.");
        } else {
            boletas.forEach(System.out::println);
        }
        System.out.println("--------------------------------\n");
    }

    @Override
    public void mostrarFacturas() {
        System.out.println("\n---[ FACTURAS REGISTRADAS ]---");
        List<String> facturas = FileUtils.leerLineas(RUTA_FACTURAS);
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            facturas.forEach(System.out::println);
        }
        System.out.println("--------------------------------\n");
    }
}