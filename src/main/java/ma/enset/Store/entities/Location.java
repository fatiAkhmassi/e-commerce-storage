package ma.enset.Store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
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

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.location",
            cascade = CascadeType.ALL)
    private Set<ProductLocation> productLocations=new HashSet<>();

    public void addProductLocation(ProductLocation productLocation){
        this.productLocations.add(productLocation);
    }


    public Location(String adress, String region, String ville, String pays) {
        this.adress = adress;
        this.region = region;
        this.ville = ville;
        this.pays = pays;
    }
}
