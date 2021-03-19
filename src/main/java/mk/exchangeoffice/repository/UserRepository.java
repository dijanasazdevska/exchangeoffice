package mk.exchangeoffice.repository;

import mk.exchangeoffice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findUserByUsernameAndPassword(String username,String password);
    Optional<User> findUserByUsername(String username);
}
