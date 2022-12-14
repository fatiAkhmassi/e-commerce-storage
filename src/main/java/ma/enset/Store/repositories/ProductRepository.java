package ma.enset.Store.repositories;

import ma.enset.Store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByLabelContainsAndCategorieIdOrRefContainsAndCategorieId(String label,Long id1,String ref,Long id, Pageable pageable);
}
