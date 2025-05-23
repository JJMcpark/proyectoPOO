package utp.poo.proyecto.entities;

public class Vendedor {

    private Long id;
    private String nombre;
    private String correo;
    private Long telefono;

    public Vendedor(Long id, String nombre, String correo, Long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void vender(Producto producto) {
        System.out.println("Vendiendo producto: " + producto.getNombre());
    }
    
    public void generarFactura(){
        System.out.println("El vendedor" + nombre + " esta generando una factura");
    }
    
    public void autorizarDescuento(){
        System.out.println("Se autoriza el descuento");
    }

}
