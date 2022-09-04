package ma.enset.Store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String adress;

    @NotEmpty
    private String region;

    @NotEmpty
    private String ville;

    @NotEmpty
    private String pays;


    @OneToMany(
            mappedBy = "location",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductLocation> products=new ArrayList<>();


    public void addProduct(Product product,Float quantite){
        ProductLocation productLocation = new ProductLocation(product, this,quantite);
        products.add(productLocation);
        product.getLocations().add(productLocation);
    }

    public void removeProduct(Product product) {
        for (Iterator<ProductLocation> iterator = products.iterator();
             iterator.hasNext(); ) {
            ProductLocation productLocation = iterator.next();

            if (productLocation.getLocation().equals(this) &&
                    productLocation.getProduct().equals(product)) {
                iterator.remove();
                productLocation.getProduct().getLocations().remove(productLocation);
                productLocation.setLocation(null);
                productLocation.setProduct(null);
            }
        }
    }

}
