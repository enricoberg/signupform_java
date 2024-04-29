package signup.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import signup.app.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer id);
}
