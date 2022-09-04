package ma.enset.Store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Embeddable
public class ProductLocation implements Serializable {
    @EmbeddedId
    private ProductLocationId id;

    //@DecimalMin("0")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    //@DecimalMin("0")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("locationId")
    private Location location;

    private Float quantite;


    public  ProductLocation(Product product,Location location,float quantite){
        this.id=new ProductLocationId(null,product,location);
        this.product=product;
        this.location=location;
        this.quantite=quantite;
    }

}
