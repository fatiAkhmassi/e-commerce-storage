package ma.enset.Store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class ProductLocationId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

}
