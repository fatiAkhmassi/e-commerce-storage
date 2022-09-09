package ma.enset.Store.repositories;

import ma.enset.Store.entities.Location;
import ma.enset.Store.entities.Product;
import ma.enset.Store.entities.ProductLocation;
import ma.enset.Store.entities.ProductLocationId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLocationRepository extends JpaRepository<ProductLocation, ProductLocationId> {
    Page<ProductLocation> findByPrimaryKeyLocation(Location id, Pageable pageable);
    Page<ProductLocation> findByPrimaryKeyProduct(Product id, Pageable pageable);
    List<ProductLocation> findByPrimaryKeyProduct(Product id);
    List<ProductLocation> findByPrimaryKeyLocation(Location id);

}
