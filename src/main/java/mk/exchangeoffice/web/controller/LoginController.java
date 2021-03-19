package mk.exchangeoffice.web.controller;

import mk.exchangeoffice.model.User;
import mk.exchangeoffice.model.exceptions.InvalidUserCredentialsException;
import mk.exchangeoffice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
private final UserService authService;

    public LoginController(UserService authService) {
        this.authService = authService;
    }


    @GetMapping
    public String getLoginPage(){
        return "login";

    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
    User user=null;
    try{
        user =this.authService.login(request.getParameter("username"),request.getParameter("password"));
    request.getSession().setAttribute("user",user);
    return "redirect:/home";
    }
    catch(InvalidUserCredentialsException ex){
        model.addAttribute("hasError",true);
        model.addAttribute("error",ex.getMessage());
        return "login";
    }

    }
}
