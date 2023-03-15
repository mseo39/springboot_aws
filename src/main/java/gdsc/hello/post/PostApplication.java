package gdsc.hello.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;

@EnableJpaAuditing //JPA Auditing  활성화
@SpringBootApplication
public class PostApplication {
	public static void main(String[] args) {

		SpringApplication.run(PostApplication.class, args);
	}
}