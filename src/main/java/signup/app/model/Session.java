package signup.app.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import signup.app.repository.UserRepository;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Data
@Entity
@Table(name = "sessions")
@NoArgsConstructor
public class Session {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;

    private String token;

    private LocalDateTime expiration;

    public Session(String username){

        LocalDateTime now = LocalDateTime.now();

        this.user=username;
        this.expiration = now.plus(10, ChronoUnit.MINUTES);
        this.token=generateSessionToken(28);


    }
    public String generateSessionToken(int targetStringLength) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

