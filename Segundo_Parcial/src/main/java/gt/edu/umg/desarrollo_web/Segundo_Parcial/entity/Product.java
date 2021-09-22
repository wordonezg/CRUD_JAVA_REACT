package gt.edu.umg.desarrollo_web.Segundo_Parcial.entity;
import javax.persistence.*;


//Notación para indicar que es una entidad
@Entity
//Tabla que corresponde a esta entidad
@Table(name = "Productos")
public class Product {
    //Llave primaria de la tabla
    @Id
    //Campo autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProduct;
    @Column(name = "producto")
    private String product;
    @Column(name = "id_marca")
    private int idMarca;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "precio_costo")
    private double costPrice;
    @Column(name = "precio_venta")
    private double salesPrice;
    @Column(name = "existencia")
    private int existence;

    public Product(){

    }

    public Product(String product, int idMarca, String description, double costPrice, double salesPrice, int existence) {
        this.product = product;
        this.idMarca = idMarca;
        this.description = description;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.existence = existence;
    }

    public int getId_producto() {
        return idProduct;
    }

    public void setId_producto(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProducto() {
        return product;
    }

    public void setProducto(String producto) {
        this.product = product;
    }

    public int getId_marca() {
        return idMarca;
    }

    public void setId_marca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public double getPrecio_costo() {
        return costPrice;
    }

    public void setPrecio_costo(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getPrecio_venta() {
        return salesPrice;
    }

    public void setPrecio_venta(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getExistencia() {
        return existence;
    }

    public void setExistencia(int existence) {
        this.existence = existence;
    }
}
