package nl.lotrac.bv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication ( exclude = {SecurityAutoConfiguration.class} )




public class FileUploadBackend01Application {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadBackend01Application.class, args);
	}

}
