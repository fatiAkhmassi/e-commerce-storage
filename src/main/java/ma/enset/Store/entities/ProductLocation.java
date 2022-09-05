package ma.enset.Store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.product",
                joinColumns = @JoinColumn(name = "PRODUCT_ID")),
        @AssociationOverride(name = "primaryKey.location",
                joinColumns = @JoinColumn(name = "LOCATION_ID")) })
public class ProductLocation implements Serializable {

    @EmbeddedId
    private ProductLocationId primaryKey = new ProductLocationId();

    @Transient
    private Product product;

    @Transient
    private Location location;

    private Float quantite;

    @Column(name = "LAST_MODIFIED")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateLastModified;

    public ProductLocation(Product product, Location location, Float quantite, Date dateLastModified) {
        primaryKey.setLocation(location);
        primaryKey.setProduct(product);
        this.product = product;
        this.location = location;
        this.quantite = quantite;
        this.dateLastModified = dateLastModified;
    }
}
