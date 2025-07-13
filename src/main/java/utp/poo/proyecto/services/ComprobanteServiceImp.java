// MEJORADO: Se integra ConsoleReader y el GestorLista para un código más limpio y robusto.
package utp.poo.proyecto.services;

import utp.poo.proyecto.entities.personas.*;
import utp.poo.proyecto.entities.productos.*;
import utp.poo.proyecto.generics.GestorLista; // MEJORADO: Se usa el gestor genérico
import utp.poo.proyecto.utils.ComprobanteUtils;
import utp.poo.proyecto.utils.ConsoleReader; // MEJORADO: Se usa la nueva utilidad
import utp.poo.proyecto.utils.FileUtils;
import java.util.Scanner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ComprobanteServiceImp implements ComprobanteService {

    // MEJORADO: Se usan rutas fuera de 'resources' para evitar reinicios de DevTools
    private static final String RUTA_BOLETAS = "data/boletas/boletas.txt";
    private static final String RUTA_FACTURAS = "data/facturas/facturas.txt"; // NUEVO

    private final Vendedor vendedorPorDefecto;

    public ComprobanteServiceImp() {
        // Esto está bien para una demo. En una app real, vendría de una base de datos.
        vendedorPorDefecto = new Vendedor();
        vendedorPorDefecto.setNombre("Josue");
        vendedorPorDefecto.setDni(71781293L);
        vendedorPorDefecto.setCorreo("josue.vendedor@cafeagusto.com");
        vendedorPorDefecto.setTelefono(987654321L);
    }

    @Override
    public void generarBoleta(Scanner sc) {
        System.out.println("\n=======================================");
        System.out.println("      ☕ ¡Bienvenido a Café Agusto! ☕");
        System.out.println("=======================================\n");

        Cliente cliente = new Cliente();
        cliente.setNombre(ConsoleReader.leerOpcional(sc, "Ingrese el nombre del cliente (opcional): "));
        
        String dniStr = ConsoleReader.leerOpcional(sc, "Ingrese el DNI del cliente (opcional): ");
        if (!dniStr.isEmpty()) {
            try {
                cliente.setDni(Long.parseLong(dniStr));
            } catch (NumberFormatException e) {
                System.out.println("DNI no válido, se dejará en blanco.");
                cliente.setDni(null);
            }
        }
        
        // MEJORADO: Usamos GestorLista para manejar la lista de productos de la venta.
        GestorLista<Producto> productos = new GestorLista<>();
        boolean agregarMas;

        do {
            Producto producto = crearProductoInteractivo(sc);
            if (producto != null) {
                productos.agregar(producto);
            }
            String respuesta = ConsoleReader.leerNoVacio(sc, "¿Desea agregar otro producto? (si/no): ");
            agregarMas = respuesta.equalsIgnoreCase("si");
        } while (agregarMas);

        if(productos.obtenerTodos().isEmpty()){
            System.out.println("No se agregaron productos. Boleta cancelada.");
            return;
        }

        guardarBoleta(vendedorPorDefecto, cliente, productos.obtenerTodos());
    }

        // NUEVO: Implementación para generar facturas
    @Override
    public void generarFactura(Scanner sc) {
        System.out.println("\n---[ GENERACIÓN DE FACTURA ]---");
        Cliente cliente = new Cliente();
        System.out.print("Ingrese el RUC del cliente: ");
        cliente.setRuc(sc.nextLine().trim());
        System.out.print("Ingrese la Razón Social del cliente: ");
        cliente.setRazonSocial(sc.nextLine().trim());

        List<Producto> productos = agregarProductos(sc);

        if (productos.isEmpty()){
            System.out.println("Venta cancelada: no se agregaron productos.");
            return;
        }
        
        // Usamos el mismo método genérico, pero con el tipo "FACTURA"
        String factura = ComprobanteUtils.imprimirComprobante("FACTURA", vendedorPorDefecto, cliente, productos);
        FileUtils.guardarTexto(RUTA_FACTURAS, factura, true);

        System.out.println("\n=== FACTURA GENERADA ===");
        System.out.println(factura);
    }
    
    // NUEVO: Método privado para no repetir la lógica de agregar productos
    private List<Producto> agregarProductos(Scanner sc){
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

    // NUEVO: Implementación para mostrar facturas
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

    @Override
    public Producto crearProductoInteractivo(Scanner sc) {
        int tipo = ConsoleReader.leerOpcion(sc, "Seleccione tipo de producto (1. Insumo, 2. Comida, 3. Bebida): ", 1, 3);

        Producto producto = null;
        String nombre;
        Integer cantidad;
        Double precio;

        switch (tipo) {
            case 1:
                producto = new Insumo();
                nombre = ConsoleReader.leerNoVacio(sc, "Nombre del insumo: ");
                cantidad = ConsoleReader.leerEnteroPositivoNull(sc, "Cantidad: ");
                precio = ConsoleReader.leerDoublePositivoNull(sc, "Precio unitario (IGV incluido): ");
                break;
            case 2:
                producto = new Comida();
                nombre = ConsoleReader.leerNoVacio(sc, "Nombre de la comida: ");
                cantidad = ConsoleReader.leerEnteroPositivoNull(sc, "Cantidad: ");
                precio = ConsoleReader.leerDoublePositivoNull(sc, "Precio unitario (IGV incluido): ");
                String paraLlevarStr = ConsoleReader.leerOpcional(sc, "¿Es para llevar? (si/no): ");
                ((Comida) producto).setParaLlevar(paraLlevarStr.equalsIgnoreCase("si"));
                break;
            case 3:
                producto = new Bebida();
                nombre = ConsoleReader.leerNoVacio(sc, "Nombre de la bebida: ");
                cantidad = ConsoleReader.leerEnteroPositivoNull(sc, "Cantidad: ");
                precio = ConsoleReader.leerDoublePositivoNull(sc, "Precio unitario (IGV incluido): ");
                String tam = ConsoleReader.leerOpcional(sc, "Tamaño (Pequeña/Mediana/Grande): ");
                ((Bebida) producto).setTamaño(tam);
                break;
            default:
                // Este caso no debería ocurrir por la validación de leerOpcion
                return null;
        }
        
        producto.setNombre(nombre);
        producto.setCantidad(cantidad != null ? cantidad : 1); // Por defecto 1 si no se especifica
        producto.setPrecioVenta(precio != null ? precio : 0.0); // Por defecto 0.0 si no se especifica
        
        return producto;
    }
    
    @Override
    public void guardarBoleta(Vendedor vendedor, Cliente cliente, List<Producto> productos) {
        String boleta = ComprobanteUtils.imprimirComprobante("BOLETA", vendedor, cliente, productos);
        FileUtils.guardarTexto(RUTA_BOLETAS, boleta, true); // Usar append
        
        System.out.println("\n=== BOLETA GENERADA Y GUARDADA ===");
        System.out.println(boleta);
        System.out.println("==================================\n");
    }

    @Override
    public void mostrarBoletas() {
        List<String> boletas = FileUtils.leerLineas(RUTA_BOLETAS);
        if (boletas.isEmpty()) {
            System.out.println("No hay boletas registradas.");
        } else {
            System.out.println("\n=== BOLETAS REGISTRADAS ===");
            boletas.forEach(System.out::println);
            System.out.println("===========================\n");
        }
    }
}
