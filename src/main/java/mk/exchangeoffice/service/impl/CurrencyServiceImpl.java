package mk.exchangeoffice.service.impl;

import mk.exchangeoffice.model.Currency;
import mk.exchangeoffice.model.exceptions.CurrencyNotFound;
import mk.exchangeoffice.repository.CurrencyRepository;
import mk.exchangeoffice.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency findById(Long id) {
        return currencyRepository.findById(id).orElseThrow(CurrencyNotFound::new);
    }

    @Override
    public Currency findByName(String name) {
        return currencyRepository.findByName(name).orElseThrow(CurrencyNotFound::new);
    }
}
