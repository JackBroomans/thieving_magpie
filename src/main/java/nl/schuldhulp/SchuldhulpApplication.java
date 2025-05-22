package nl.schuldhulp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "nl.schuldhulp.model.repository")
public class SchuldhulpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchuldhulpApplication.class, args);
    }

}
