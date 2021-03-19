package mk.exchangeoffice.model;

import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Currency  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    private String country;
    private String countryEN;



    public Currency(Long id, String name, String country) {

        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Currency() {
    }
}
