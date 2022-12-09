package com.ironhack.backendProject;


import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendProjectApplication implements CommandLineRunner {
	@Autowired
	UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateAdminDTO createAdminDTO = new CreateAdminDTO("admin", "admin");
		userService.createAdmin(createAdminDTO);

}
}