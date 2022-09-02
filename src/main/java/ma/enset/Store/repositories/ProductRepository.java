package ma.enset.Store.repositories;

import ma.enset.Store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByLabelContainsOrRefContains(String label,String ref, Pageable pageable);
}
