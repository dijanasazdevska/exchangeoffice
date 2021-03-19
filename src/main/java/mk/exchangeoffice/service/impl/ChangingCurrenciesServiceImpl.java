package mk.exchangeoffice.service.impl;

import mk.exchangeoffice.model.ChangingCurrencies;
import mk.exchangeoffice.model.Currency;
import mk.exchangeoffice.model.exceptions.CurrencyNotFound;
import mk.exchangeoffice.repository.ChangingCurrenciesRepository;
import mk.exchangeoffice.service.ChangingCurrenciesService;
import mk.exchangeoffice.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangingCurrenciesServiceImpl  implements ChangingCurrenciesService {
    private final ChangingCurrenciesRepository changingCurrenciesRepository;
    private final CurrencyService currencyService;

    public ChangingCurrenciesServiceImpl(ChangingCurrenciesRepository changingCurrenciesRepository, CurrencyService currencyService) {
        this.changingCurrenciesRepository = changingCurrenciesRepository;
        this.currencyService = currencyService;
    }

    @Override
    public List<ChangingCurrencies> findAll() {
        return changingCurrenciesRepository.findAll();
    }

    @Override
    public ChangingCurrencies save(Long id, int amount, float buying, float selling) {
    return changingCurrenciesRepository.save(new ChangingCurrencies(id,amount,buying,selling));
    
    }
    @Override
    public ChangingCurrencies findById(Long id){
       return changingCurrenciesRepository.findById(id).orElseThrow(CurrencyNotFound::new);
    }

    @Override
    public float exchange(String currency_from, String currency_to, int amount) {

        Currency c_from= currencyService.findByName(currency_from);
        Currency c_to= currencyService.findByName(currency_to);
        ChangingCurrencies cc= null;
        if(!c_from.getName().equals("MKD")){
            cc= changingCurrenciesRepository.findById(c_from.getId()).orElseThrow(CurrencyNotFound::new);

        }
        else
        {
            cc= changingCurrenciesRepository.findById(c_to.getId()).orElseThrow(CurrencyNotFound::new);

        }
      float result=0;
       if(c_from.getName().equals("MKD")){
           result=amount/cc.getSelling();
       }
       else {
           result=amount*cc.getBuying();
       }
       return result;
    }
}
