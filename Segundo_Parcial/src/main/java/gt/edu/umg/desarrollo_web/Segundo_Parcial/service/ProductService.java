package gt.edu.umg.desarrollo_web.Segundo_Parcial.service;

import gt.edu.umg.desarrollo_web.Segundo_Parcial.entity.Product;
import gt.edu.umg.desarrollo_web.Segundo_Parcial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @NotNull
    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(int idTorre){
        return  productRepository.findById(idTorre);
    }

    public Optional<Product> getByProductName(String productName){
        return productRepository.findByProduct(productName);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(int idProduct){
        productRepository.deleteById(idProduct);
    }

    public boolean existsByIdProduct(int idProduct){
        return productRepository.existsById(idProduct);
    }

    public boolean existsByProductName(String productName){
        return productRepository.existsByProduct(productName);
    }

}
