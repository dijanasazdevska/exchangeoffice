package mk.exchangeoffice.web.controller;

import mk.exchangeoffice.model.Currency;
import mk.exchangeoffice.service.ChangingCurrenciesService;
import mk.exchangeoffice.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    private final CurrencyService currencyService;
    private final ChangingCurrenciesService changingCurrenciesService;

    public CalculatorController(CurrencyService currencyService, ChangingCurrenciesService changingCurrenciesService) {
        this.currencyService = currencyService;
        this.changingCurrenciesService = changingCurrenciesService;
    }

    @GetMapping
    public String getPage(Model model,@RequestParam(required = false) String lang){
        if(lang==null){
            lang="MK";
        }
        model.addAttribute("currencies",currencyService.findAll().stream().sorted(Comparator.comparing(Currency::getId)).collect(Collectors.toList()));
        model.addAttribute("bodyContent","calculator");
        model.addAttribute("bodyTitle","Калкулатор");
        model.addAttribute("lang",lang);
        return "master-template";
    }
    @PostMapping
    public String postPage(@RequestParam String currency_from, @RequestParam String currency_to,@RequestParam int value_from,Model model,@RequestParam(required = false) String lang){
        if(lang==null){
            lang="MK";
        }
        model.addAttribute("currencies",currencyService.findAll().stream().sorted(Comparator.comparing(Currency::getId)).collect(Collectors.toList()));
        model.addAttribute("bodyContent","calculator");
        model.addAttribute("bodyTitle","Калкулатор");
        model.addAttribute("currency_from",currency_from);
        model.addAttribute("currency_to",currency_to);
        model.addAttribute("value_from",value_from);
        model.addAttribute("value_to",changingCurrenciesService.exchange(currency_from,currency_to,value_from));
        model.addAttribute("lang",lang);

        return "master-template";
    }
}
