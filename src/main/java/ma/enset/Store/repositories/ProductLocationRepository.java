package ma.enset.Store.repositories;

import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLocationRepository extends JpaRepository<ProductLocation,Long> {
}
