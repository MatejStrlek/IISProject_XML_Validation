package hr.algebra.iisproject.controllers;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.services.TvMovieShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import utils.XmlUtil;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
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
            Validator validator = initValidator();
            InputStream inputStream = file.getInputStream();
            validator.validate(new StreamSource(inputStream));

            /*inputStream = file.getInputStream();
            TvMovieShow tvMovieShow = XmlUtil.parseXmlToEntity(inputStream);

            tvMovieShowService.saveTvMovieShow(tvMovieShow);*/

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

