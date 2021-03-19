package mk.exchangeoffice.service;

import mk.exchangeoffice.model.ChangingCurrencies;
import mk.exchangeoffice.model.Currency;

import java.util.List;

public interface ChangingCurrenciesService {
    List<ChangingCurrencies> findAll();
    ChangingCurrencies save(Long id, int amount, float buying, float selling);
    ChangingCurrencies findById(Long id);
    float exchange(String currency_from, String currency_to,int amount);

}
