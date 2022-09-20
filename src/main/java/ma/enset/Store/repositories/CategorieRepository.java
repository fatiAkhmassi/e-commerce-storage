package ma.enset.Store.repositories;

import ma.enset.Store.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Page<Categorie> findByLabelContainsOrDescriptionContains(String label, String description, Pageable pageable);
}
