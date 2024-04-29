package signup.app.dto;


import lombok.Data;

@Data
public class ValidationRequest {

    private String email;
    private String token;
}
