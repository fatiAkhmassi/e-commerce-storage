package ma.enset.Store.repositories;

import ma.enset.Store.entities.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Page<Location> findByAdressContains(String keyword, Pageable pageable);
}
