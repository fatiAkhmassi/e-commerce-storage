package ma.enset.Store.repositories;

import ma.enset.Store.entities.ProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLocationRepository extends JpaRepository<ProductLocation,Long> {
}
