package utp.poo.proyecto.services;

import utp.poo.proyecto.entities.personas.*;
import utp.poo.proyecto.entities.productos.*;
import utp.poo.proyecto.utils.BoletaUtils;
import utp.poo.proyecto.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ComprobanteServiceImp implements ComprobanteService {

    private static final String RUTA_BOLETAS = "src/main/resources/boletas/boletas.txt";

    @Override
    public void generarBoleta(Scanner sc) {
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

        String boleta = BoletaUtils.imprimirBoleta(vendedor, cliente, productos);
        FileUtils.guardarTexto("src/main/resources/boletas/boletas.txt", boleta, true);

        System.out.println(boleta);
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
        for (String linea : boletas) {
            System.out.println(linea);
        }
    }
}
