package ma.enset.Store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Categorie implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORIE_ID")
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String label;

    @Size(min = 3,max = 100)
    private String description;


    private String CategorieImage;

    @JsonIgnore
    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Product> products=new HashSet<>();

    public void addProduct(Product product){
        this.products.add(product);
    }


    public Categorie(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
