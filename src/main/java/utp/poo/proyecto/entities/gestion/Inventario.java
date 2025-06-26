package utp.poo.proyecto.entities.gestion;

import java.util.Map;

import lombok.*;
import utp.poo.proyecto.entities.personas.Proveedor;
import utp.poo.proyecto.entities.productos.Producto;

@Getter
@Setter
@NoArgsConstructor
public class Inventario {

    private Long idInventario;
    private Map<Long, Producto> productos;
    private Proveedor proveedor;
    private int cantidad;
    private double precioCompra;
    private double precioVenta;
    private String fechaIngreso;
    private String fechaVencimiento;
    private int stock;

    public Inventario(Long idInventario, Map<Long, Producto> productos, Proveedor proveedor, int cantidad, double precioCompra, double precioVenta, String fechaIngreso, String fechaVencimiento) {
        this.idInventario = idInventario;
        this.productos = productos;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
    }

     public boolean actualizarStock(int cantidad) {
        if (this.stock + cantidad < 0) {
            System.out.println("Error: Stock insuficiente para realizar esta operación.");
            return false;
        }
        this.stock += cantidad;
        System.out.println("Stock actualizado. Nuevo stock: " + this.stock);
        return true;
    }

    
        public int getStock() {
            return stock;
        }
        
        public String getFechaIngreso(){     
           
            return fechaIngreso;         
        }
        
        public double getPrecioVenta(){
            
            return precioVenta;
        }
        
        public long getIdInventario(){
            
            return idInventario;
        }
        
        public String getFechaVencimiento(){
            
            return fechaVencimiento;
        }
        
        
        public void contactarProveedor(Proveedor proveedor){
            System.out.println("Se está contactando con el proveedor " + proveedor.getNombre());
        }
}
