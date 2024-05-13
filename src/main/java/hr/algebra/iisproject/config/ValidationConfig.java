package hr.algebra.iisproject.config;

import hr.algebra.iisproject.services.XmlValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    @Bean
    public XmlValidationService xmlValidationService() {
        return new XmlValidationService();
    }
}

