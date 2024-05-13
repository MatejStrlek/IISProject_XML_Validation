package hr.algebra.iisproject.services;

import com.thaiopensource.validate.ValidationDriver;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@Service
public class RNGValidationService {
    public String validate(InputStream xmlStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true);

        try (xmlStream; ps; xmlStream) {
            PrintStream oldErr = System.err;
            System.setErr(ps);
            ValidationDriver driver = new ValidationDriver();
            if (!driver.loadSchema(new InputSource(getClass().getResourceAsStream("/RNGValidator/tvMovieShows.rng")))) {
                return "Failed to load the RNG schema.";
            }

            if (!driver.validate(new InputSource(xmlStream))) {
                System.setErr(oldErr);
                String errors = baos.toString(StandardCharsets.UTF_8);
                return errors.isEmpty() ? "Validation failed, unknown error." : errors;
            }
            return "XML is valid according to the RNG schema.";
        } catch (SAXException e) {
            return "Validation error: " + e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

