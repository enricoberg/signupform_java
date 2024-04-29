package signup.app.dto;


import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;


@Data
public class SignUpRequest {

    private String email;
    private String password;
    private String firstname;
    private String secondname;
    private String addressstreet;
    private String addresscity;
    private Integer addressnumber;
    private String addressprovince;
    private LocalDate birthdate;

}
