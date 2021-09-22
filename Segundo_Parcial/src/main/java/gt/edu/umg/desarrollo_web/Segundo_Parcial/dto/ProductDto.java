package gt.edu.umg.desarrollo_web.Segundo_Parcial.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductDto {
    //Notación para especificar que el campo no puede venir vacio
    @NotBlank
    private String producto;
    //Notación para indicar que el tamaño minimo debe ser 0
    @Min(1)
    private int id_marca;

    private String descripcion;
    private double precio_costo;
    private double precio_venta;
    private int existencia;

    public ProductDto() {
    }

    public ProductDto(String producto, int id_marca,String descripcion,double precio_costo,double precio_venta,int existencia) {
        this.producto = producto;
        this.id_marca = id_marca;
        this.descripcion=descripcion;
        this.precio_costo=precio_costo;
        this.precio_venta=precio_venta;
        this.existencia=existencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
}
