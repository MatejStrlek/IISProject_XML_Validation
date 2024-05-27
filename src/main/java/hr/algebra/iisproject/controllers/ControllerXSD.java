package hr.algebra.iisproject.controllers;

import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.services.TvMovieShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import utils.XmlUtils;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/xsd")
public class ControllerXSD {
    private final TvMovieShowService tvMovieShowService;

    public ControllerXSD(TvMovieShowService tvMovieShowService) {
        this.tvMovieShowService = tvMovieShowService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload.");
        }

        try {
            Validator validator = XmlUtils.initXSDValidator(getClass().getClassLoader().getResourceAsStream("XSDValidator/tvMovieShows.xsd"));

            // XSD validation
            InputStream inputStreamValidation = file.getInputStream();
            validator.validate(new StreamSource(inputStreamValidation));
            inputStreamValidation.close();

            // XML parsing
            InputStream inputStreamParsing = file.getInputStream();
            TvMovieShows tvMovieShows = XmlUtils.parseXmlToEntity(inputStreamParsing);
            inputStreamParsing.close();

            //H2 database save
            saveShowsToDatabase(tvMovieShows);

            return ResponseEntity.ok("XML is valid based on XSD file.");
        } catch (SAXException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error validating file: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading file: " + e.getMessage());
        } catch (JAXBException e) {
            System.out.println("Error parsing XML: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error parsing XML: " + e.getMessage());
        }
    }

    private void saveShowsToDatabase(TvMovieShows tvMovieShows) {
        tvMovieShowService.saveTvMovieShows(tvMovieShows);
    }

}

