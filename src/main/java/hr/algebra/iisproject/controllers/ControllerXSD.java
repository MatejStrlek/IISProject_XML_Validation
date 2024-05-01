package hr.algebra.iisproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/xsd")
public class ControllerXSD {
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload.");
        }

        try {
            Validator validator = initValidator();
            InputStream inputStream = file.getInputStream();
            validator.validate(new StreamSource(inputStream));
            return ResponseEntity.ok("XML is valid.");
        } catch (SAXException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error validating file: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading file: " + e.getMessage());
        }
    }

    private Validator initValidator() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getClass().getClassLoader().getResourceAsStream("XSDValidator/tvMovieShows.xsd"));
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }
}

