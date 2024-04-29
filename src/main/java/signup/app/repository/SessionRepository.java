package signup.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import signup.app.model.Session;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository  extends JpaRepository<Session, Integer> {

    List<Session> findAll();
    Optional<Session> findByUserAndToken(String user, String token);
}
