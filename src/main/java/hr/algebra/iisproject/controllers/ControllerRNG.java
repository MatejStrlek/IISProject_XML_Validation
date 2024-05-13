package hr.algebra.iisproject.controllers;

import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.services.TvMovieShowService;
import hr.algebra.iisproject.services.RNGValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utils.XmlUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/rng")
public class ControllerRNG {
    private final TvMovieShowService tvMovieShowService;
    @Autowired
    private RNGValidationService validationService;

    public ControllerRNG(TvMovieShowService tvMovieShowService) {
        this.tvMovieShowService = tvMovieShowService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a non-empty XML file.");
        }

        try (InputStream inputStream = file.getInputStream()) {
            // Validate the XML file against the RNG schema
            if (validationService.validate(inputStream)) {
                // XML parsing
                InputStream inputStreamParsing = file.getInputStream();
                TvMovieShows tvMovieShows = XmlUtils.parseXmlToEntity(inputStreamParsing);
                inputStreamParsing.close();

                // Save the parsed data to the database
                saveShowsToDatabase(tvMovieShows);

                return ResponseEntity.ok("XML is valid according to the RNG schema.");
            } else {
                return ResponseEntity.badRequest().body("XML does not conform to the RNG schema.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO error: " + e.getMessage());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveShowsToDatabase(TvMovieShows tvMovieShows) {
        tvMovieShowService.saveTvMovieShows(tvMovieShows);
    }
}
