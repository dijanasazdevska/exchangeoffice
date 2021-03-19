package mk.exchangeoffice.service;

import mk.exchangeoffice.model.User;
import mk.exchangeoffice.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword);
     User login(String username, String password) ;


    }
