package gt.edu.umg.desarrollo_web.Segundo_Parcial.repository;

import gt.edu.umg.desarrollo_web.Segundo_Parcial.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends  JpaRepository<Product,Integer> {
    Optional<Product> findByProduct(String productName);

    boolean existsByProduct(String productName);


}
