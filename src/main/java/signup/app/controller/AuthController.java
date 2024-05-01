package signup.app.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import signup.app.dto.SignInRequest;
import signup.app.dto.SignUpRequest;
import signup.app.model.Session;
import signup.app.model.User;
import signup.app.repository.SessionRepository;
import signup.app.repository.UserRepository;
import java.util.Optional;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SessionRepository sessionRepository;



    //FUNCTION TO SIGNIN AND GET THE SESSION TOKEN
    @PostMapping("/signin")
    public String signin(@RequestBody SignInRequest signinrequest){
        Optional<User> opt_user= userRepository.findByEmail(signinrequest.getEmail());
        if(!opt_user.isPresent()) return "0"; // NO USER FOUND WITH THAT EMAIL
        User user=opt_user.get();
        if(!user.getPassword().equals(signinrequest.getPassword())) return "1"; //WRONG PASSWORD
        Session session = new Session(user.getEmail());
        sessionRepository.save(session);
        return session.getToken(); //CORRECT PASSWORD, RETURN TOKEN
    }



    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest signuprequest){
        //CONTROLLER FOR HANDLING SIGNUP VALIDATION AND ADDING NEW USER TO DATABASE
        //THE CONTROLLER RETURNS A NUMERIC CODE FOR IDENTIFYING INPUT ERRORS
        if(signuprequest.getFirstname()==null || signuprequest.getSecondname()==null || signuprequest.getAddressstreet()==null || signuprequest.getAddressnumber()==null || signuprequest.getAddresscity()==null || signuprequest.getAddressprovince()==null || signuprequest.getBirthdate()==null || signuprequest.getPassword()==null || signuprequest.getEmail()==null) return "0"; //ERROR CODE 0: MISSING PARAMETERS
        if(signuprequest.getFirstname().isEmpty() || signuprequest.getSecondname().isEmpty() || signuprequest.getAddressstreet().isEmpty()  || signuprequest.getAddresscity().isEmpty() || signuprequest.getAddressprovince().isEmpty() || signuprequest.getPassword().isEmpty()|| signuprequest.getEmail().isEmpty()) return "0"; //ERROR CODE 0: MISSING PARAMETERS
        Optional<User> opt_user=userRepository.findByEmail(signuprequest.getEmail());
        if(opt_user.isPresent()) return "2"; //ERROR CODE 2: USER ALREADY EXISTS
        if(!signuprequest.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) return "3"; //ERROR CODE 3: INVALID EMAIL
        if(!signuprequest.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$")) return "4"; //ERROR CODE 4: PASSWORD TOO WEAK
        //SAVE TO DB THE NEW USER IF ALL CONTROLS ARE PASSED
        User new_user= new User(signuprequest);
        userRepository.save(new_user);



        return "1";
    }
}
