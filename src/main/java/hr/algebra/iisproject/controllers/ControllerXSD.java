package hr.algebra.iisproject.controllers;

import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/api/xsd")
public class ControllerXSD {
    @GetMapping("/upload")
    public String showForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws SAXException {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "upload";
        }

        Validator validator = initValidator();

        try {
            InputStream inputStream = file.getInputStream();
            validator.validate(new StreamSource(inputStream));
            model.addAttribute("message", "Valid XML!");
        } catch (SAXException e) {
            model.addAttribute("message", "Error validating file: " + e.getMessage());
        } catch (IOException e) {
            model.addAttribute("message", "Error reading file: " + e.getMessage());
        }
        return "upload";
    }

    private Validator initValidator() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getClass().getClassLoader().getResourceAsStream("XSDValidator/tvMovieShows.xsd"));
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }
}

