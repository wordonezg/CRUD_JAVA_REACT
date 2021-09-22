package gt.edu.umg.desarrollo_web.Segundo_Parcial.controller;
import gt.edu.umg.desarrollo_web.Segundo_Parcial.dto.Message;
import gt.edu.umg.desarrollo_web.Segundo_Parcial.dto.ProductDto;
import gt.edu.umg.desarrollo_web.Segundo_Parcial.entity.Product;
import gt.edu.umg.desarrollo_web.Segundo_Parcial.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Notación para indicar que es un controlador de tipo Rest
@RestController
//Notación para indicar el contexto de nuestros endpoint es decir /torre/nombreServicio
@RequestMapping("/producto")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "http://localhost:3000/")
public class ProductController {
    /*El nombre de los productos es unico,
    en la creación y actualizacón se hace la validación*/

    //Inyección de dependencias
    @Autowired
    ProductService productService;

    //Se le indica el tipo de petición asi como el nombre del servicio
    @GetMapping("/listaProductos")
    public ResponseEntity<List<Product>> listProducts(){

        List<Product> products = productService.listProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/detalleProducto/{idProduct}")
    public ResponseEntity<Product> productById(@PathVariable("idProduct") int idProduct){

        if (!productService.existsByIdProduct(idProduct))
            return new ResponseEntity(new Message("No existe el producto"), HttpStatus.NOT_FOUND);

        Product product = productService.getProduct(idProduct).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @GetMapping("/detalleProductoNombre/{productName}")
    public ResponseEntity<Product> productByName(@PathVariable("productName") String productName){

        if (!productService.existsByProductName(productName))
            return new ResponseEntity(new Message("No existe el producto"), HttpStatus.NOT_FOUND);

        Product product = productService.getByProductName(productName).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    //Con el ? le decimos que no devulve ningún tipo de dato
    //El body va a ser un JSON
    //Aqui se usa el apache commons lang
    // El import de StringUtils es import org.apache.commons.lang3.StringUtils;
    @PostMapping("/crearProducto")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){

        if(StringUtils.isBlank(productDto.getProducto()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(productDto.getId_marca()<0 || (Integer) productDto.getId_marca() == null)
            return new ResponseEntity(new Message("La Marca no es válida"), HttpStatus.BAD_REQUEST);

        if(productService.existsByProductName(productDto.getProducto()))
            return new ResponseEntity(new Message("Ya existe un producto con ese nombre"), HttpStatus.BAD_REQUEST);

        Product product = new Product(productDto.getProducto(), productDto.getId_marca(),productDto.getDescripcion(),productDto.getPrecio_costo(),productDto.getPrecio_venta(),productDto.getExistencia());
        productService.saveProduct(product);
        return new ResponseEntity(new Message("Producto creado"), HttpStatus.OK);
    }

    @PutMapping("/actualizarProducto/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable("idProduct") int idProduct, @RequestBody ProductDto productDto){

        if (!productService.existsByIdProduct(idProduct))
            return new ResponseEntity(new Message("No existe el producto"), HttpStatus.NOT_FOUND);

        if (productService.existsByProductName(productDto.getProducto())
                && productService.getByProductName(productDto.getProducto()).get().getId_producto() != idProduct)
            return new ResponseEntity(new Message("El nombre del producto ya existe"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(productDto.getProducto()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(productDto.getId_marca()<=0 || (Integer) productDto.getId_marca() == null)
            return new ResponseEntity(new Message("La marca no es válida"), HttpStatus.BAD_REQUEST);

        Product product = productService.getProduct(idProduct).get();
        product.setProducto(productDto.getProducto());
        product.setDescripcion(productDto.getDescripcion());
        product.setId_marca(productDto.getId_marca());
        product.setPrecio_costo(productDto.getPrecio_costo());
        product.setPrecio_venta(productDto.getPrecio_venta());
        product.setExistencia(productDto.getExistencia());
        productService.saveProduct(product);
        return new ResponseEntity(new Message("Producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarProducto/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable("idProduct") int idProduct){
        if (!productService.existsByIdProduct(idProduct))
            return new ResponseEntity(new Message("No existe el producto"), HttpStatus.NOT_FOUND);
        productService.deleteProduct(idProduct);
        return new ResponseEntity(new Message("Producto eliminado"), HttpStatus.OK);
    }
}
