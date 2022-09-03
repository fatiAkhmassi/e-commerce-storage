package ma.enset.Store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLocation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@DecimalMin("0")
    private Long product_id;

    //@DecimalMin("0")
    private Long location_id;

    private Float quantite;
}
