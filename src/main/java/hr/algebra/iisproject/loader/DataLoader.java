package hr.algebra.iisproject.loader;

import hr.algebra.iisproject.models.AppUser;
import hr.algebra.iisproject.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.save(new AppUser("user", passwordEncoder.encode("password")));
        };
    }
}

