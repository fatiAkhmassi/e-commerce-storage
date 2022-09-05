package ma.enset.Store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String ref;

    @NotEmpty
    private String label;

    @Size(min = 3,max = 100)
    private String description;

    @DecimalMin("1")
    private Float price;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.product",
            cascade = CascadeType.ALL)
    private List<ProductLocation> productLocations=new ArrayList<>();

    public void addProductLocation(ProductLocation productLocation){
        this.productLocations.add(productLocation);
    }


    public Product(String ref, String label, String description, Float price) {
        this.ref = ref;
        this.label = label;
        this.description = description;
        this.price = price;
    }
}
