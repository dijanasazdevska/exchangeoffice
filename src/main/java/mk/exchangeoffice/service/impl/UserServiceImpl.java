package mk.exchangeoffice.service.impl;

import mk.exchangeoffice.model.User;
import mk.exchangeoffice.model.enumerations.Role;
import mk.exchangeoffice.model.exceptions.InvalidUserCredentialsException;
import mk.exchangeoffice.model.exceptions.InvalidUsernameOrPasswordException;
import mk.exchangeoffice.model.exceptions.PasswordsDoNotMatchException;
import mk.exchangeoffice.model.exceptions.UsernameAlreadyExistsException;
import mk.exchangeoffice.repository.UserRepository;
import mk.exchangeoffice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findById(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        return userRepository.findUserByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);

    }

}
