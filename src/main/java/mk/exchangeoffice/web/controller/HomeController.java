package mk.exchangeoffice.web.controller;

import mk.exchangeoffice.model.ChangingCurrencies;
import mk.exchangeoffice.service.ChangingCurrenciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ChangingCurrenciesService changingCurrenciesService;

    public HomeController(ChangingCurrenciesService changingCurrenciesService) {
        this.changingCurrenciesService = changingCurrenciesService;
    }

    @GetMapping
    private String getPage(Model model,@RequestParam(required = false) String lang){
        if(lang==null){
            lang="MK";
        }
    model.addAttribute("changingCurrencies",changingCurrenciesService.findAll().stream().sorted(Comparator.comparing((ChangingCurrencies::getCurrency_id))).collect(Collectors.toList()));
    model.addAttribute("bodyContent","list");
    model.addAttribute("bodyTitle","Курсна листа");
    model.addAttribute("lang",lang);
    return "master-template";
}
@GetMapping("/map")
public String getMapPage(Model model,@RequestParam(required = false) String lang){
        if(lang==null){
            lang="MK";
        }
        model.addAttribute("bodyContent","map");
        model.addAttribute("bodyTitle","Локација");
        model.addAttribute("lang",lang);
        return "master-template";
}
@GetMapping("/contact")
public String getContactPage(Model model,@RequestParam(required = false) String lang){
    if(lang==null){
        lang="MK";
    }
    model.addAttribute("bodyContent","contact");
    model.addAttribute("bodyTitle","Контакт");
    model.addAttribute("lang",lang);
    return "master-template";

}
@GetMapping("/admin/change/{id}")
    public String getPage(@PathVariable Long id, Model model,@RequestParam(required = false) String lang){
    if(lang==null){
        lang="MK";
    }
        model.addAttribute("c",changingCurrenciesService.findById(id));
    model.addAttribute("bodyContent","change");
    model.addAttribute("bodyTitle","Промени");
    model.addAttribute("lang",lang);

    return "master-template";



}
@PostMapping("/admin/change/{id}")
    public String postPage(@PathVariable Long id, @RequestParam float buying,@RequestParam float selling, @RequestParam int amount){

        changingCurrenciesService.save(id,amount,buying,selling);
        return "redirect:/";
}


}
