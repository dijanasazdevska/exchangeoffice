package mk.exchangeoffice.service;

import mk.exchangeoffice.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> findAll();
    Currency findById(Long id);
    Currency findByName(String name);
}
