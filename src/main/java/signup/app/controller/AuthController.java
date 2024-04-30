package signup.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SessionAttributeMethodArgumentResolver;
import signup.app.dto.SignInRequest;
import signup.app.dto.SignUpRequest;

import signup.app.dto.ValidationRequest;
import signup.app.model.Session;
import signup.app.model.User;
import signup.app.repository.SessionRepository;
import signup.app.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SessionRepository sessionRepository;



    @GetMapping("/getallusers")
    public List<User> getusers(){
        return userRepository.findAll();

    }

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

    //FUNCTION THAT VALIDATES THE SESSION, RETURN TRUE IF PRESENT IN DATABASE
    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody ValidationRequest validationRequest){
        Optional<Session> opt_session=sessionRepository.findByUserAndToken(validationRequest.getEmail(),validationRequest.getToken());
        if(!opt_session.isPresent()) return ResponseEntity.status(500).body("Session not found");
        Session session= opt_session.get();
        if(session.getExpiration().isBefore(LocalDateTime.now())) {
            sessionRepository.delete(session);
            return ResponseEntity.status(500).body("Session expired");
        }
        return ResponseEntity.ok("User validated");
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

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try{
            userRepository.deleteById(id);
        }catch(Exception e ){
            return ResponseEntity.status(500).body("Failed to delete the user");
        }
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/updateuser/{id}")
    //THIS CONTROLLER SEARCHES FOR EXISTING USER WITHS SPECIFIED ID AND OVERWRITES THE PARAMETERS
    public ResponseEntity<String> updateuser(@PathVariable Integer id, @RequestBody SignUpRequest updateUserRequest){
        //VALIDATE THE REQUEST
        if(updateUserRequest.getEmail()==null || updateUserRequest.getPassword()==null || updateUserRequest.getFirstname()==null || updateUserRequest.getSecondname()==null || updateUserRequest.getAddressstreet()==null || updateUserRequest.getAddressnumber()==null || updateUserRequest.getAddresscity()==null || updateUserRequest.getAddressprovince()==null || updateUserRequest.getBirthdate()==null) return ResponseEntity.status(500).body("Invalid request");
        if(updateUserRequest.getEmail().isEmpty() || updateUserRequest.getPassword().isEmpty() || updateUserRequest.getFirstname().isEmpty() || updateUserRequest.getSecondname().isEmpty() || updateUserRequest.getAddressstreet().isEmpty() || updateUserRequest.getAddresscity().isEmpty()  || updateUserRequest.getAddressprovince().isEmpty() ) return ResponseEntity.status(500).body("Invalid request");
        //VERIFY THE USER EXISTS
        Optional<User> opt_user=userRepository.findById(id);
        if(!opt_user.isPresent()) return ResponseEntity.status(500).body("The user you requested to update does not exist");
        User actual_user=opt_user.get();
        //CHANGE ONLY THE PARAMETERS SENT WITH THE REQUEST
        
        actual_user.setEmail(updateUserRequest.getEmail());
        actual_user.setPassword(updateUserRequest.getPassword());
        actual_user.setFirstname(updateUserRequest.getFirstname());
        actual_user.setSecondname(updateUserRequest.getSecondname());
        actual_user.setAddressstreet(updateUserRequest.getAddressstreet());
        actual_user.setAddressnumber(updateUserRequest.getAddressnumber());
        actual_user.setAddresscity(updateUserRequest.getAddresscity());
        actual_user.setAddressprovince(updateUserRequest.getAddressprovince());
        actual_user.setBirthdate(updateUserRequest.getBirthdate());
        userRepository.save(actual_user);

        return ResponseEntity.ok("User updated successfully");
    }
}
