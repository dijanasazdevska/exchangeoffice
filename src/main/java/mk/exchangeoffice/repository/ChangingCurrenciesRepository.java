package mk.exchangeoffice.repository;

import mk.exchangeoffice.model.ChangingCurrencies;
import mk.exchangeoffice.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangingCurrenciesRepository extends JpaRepository<ChangingCurrencies, Long> {

}
