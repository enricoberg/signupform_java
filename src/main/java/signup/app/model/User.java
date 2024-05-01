package signup.app.model;



import jakarta.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;
import signup.app.dto.SignUpRequest;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstname;

    @Column(nullable = false, length = 20)
    private String secondname;







    @Column(nullable = false)
    private String addressstreet;

    @Column(nullable = false)
    private Integer addressnumber;

    @Column(nullable = false)
    private String addresscity;

    @Column(nullable = false, length = 2)
    private String addressprovince;

    @Column(nullable = false)
    private LocalDate birthdate;

    public User(SignUpRequest signUpRequest){

        email=signUpRequest.getEmail().toLowerCase();
        password=signUpRequest.getPassword();
        firstname=signUpRequest.getFirstname().toUpperCase();
        secondname=signUpRequest.getSecondname().toUpperCase();
        addressstreet=signUpRequest.getAddressstreet().toUpperCase();
        addressnumber=signUpRequest.getAddressnumber();
        addresscity=signUpRequest.getAddresscity().toUpperCase();
        addressprovince=signUpRequest.getAddressprovince().toUpperCase();
        birthdate=signUpRequest.getBirthdate();

    }

}
