package signup.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import signup.app.repository.UserRepository;

@SpringBootApplication
public class AppApplication {


	public static void main(String[] args) {



		SpringApplication.run(AppApplication.class, args);
		System.out.println("SERVER RUNNING.....");
		
	}

}
