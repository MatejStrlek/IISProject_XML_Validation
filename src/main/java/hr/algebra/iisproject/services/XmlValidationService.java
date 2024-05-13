package hr.algebra.iisproject.services;

import com.thaiopensource.validate.ValidationDriver;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@Service
public class XmlValidationService {
    public boolean validate(InputStream xmlStream) {
        try (xmlStream) {
            ValidationDriver driver = new ValidationDriver();
            // Load the schema
            if (!driver.loadSchema(new InputSource(getClass().getResourceAsStream("/RNGValidator/tvMovieShows.rng")))) {
                throw new SAXException("Schema could not be loaded");
            }
            // Validate the XML against the loaded schema
            return driver.validate(new InputSource(xmlStream));
        } catch (SAXException | IOException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}

