package signup.app.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import signup.app.dto.SignUpRequest;
import signup.app.model.User;
import signup.app.repository.UserRepository;

@RequestMapping("/accounts")
@RequiredArgsConstructor
@RestController
public class UserController {

    @Autowired
    private final UserRepository userRepository;   


    //FUNCTION THAT RETURNS FULL LIST OF USERS
    @GetMapping("/getallusers")
    public List<User> getusers(){
        return userRepository.findAll();
    }
    

    //FUNCTION TO DELETE USER FROM DB 
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
