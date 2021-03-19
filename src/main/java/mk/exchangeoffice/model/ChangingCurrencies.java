package mk.exchangeoffice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ChangingCurrencies implements Serializable {

@JoinColumn(name="currency_id",insertable = false,updatable = false,referencedColumnName = "id")
@ManyToOne
private Currency currency;

@Id
@Column(name="currency_id")
private Long currency_id;
    private int amount;
    private float buying;
    private float selling;

    public ChangingCurrencies() {
    }

    public ChangingCurrencies(Long currency_id, int amount, float buying, float selling) {
        this.currency_id = currency_id;
        this.amount = amount;
        this.buying = buying;
        this.selling = selling;

    }
}
