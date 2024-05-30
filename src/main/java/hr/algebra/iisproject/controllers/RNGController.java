package hr.algebra.iisproject.controllers;

import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.services.TvMovieShowService;
import hr.algebra.iisproject.services.RNGValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utils.XmlUtils;

import java.io.InputStream;

@RestController
@RequestMapping("/api/rng")
public class RNGController {
    private final TvMovieShowService tvMovieShowService;
    @Autowired
    private RNGValidationService validationService;

    public RNGController(TvMovieShowService tvMovieShowService) {
        this.tvMovieShowService = tvMovieShowService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a non-empty XML file.");
        }

        try (InputStream inputStream = file.getInputStream()) {
            // Validate the XML file against the RNG schema
                String result = validationService.validate(inputStream);
                if (result.startsWith("XML is valid")) {
                    // XML parsing
                    InputStream inputStreamParsing = file.getInputStream();
                    TvMovieShows tvMovieShows = XmlUtils.parseXmlToEntity(inputStreamParsing);
                    inputStreamParsing.close();

                    // Save the parsed data to the database
                    saveShowsToDatabase(tvMovieShows);

                    return ResponseEntity.ok(result);
                } else {
                    return ResponseEntity.badRequest().body(result);
                }
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error during XML processing: " + e.getMessage());
            }
    }

    private void saveShowsToDatabase(TvMovieShows tvMovieShows) {
        tvMovieShowService.saveTvMovieShows(tvMovieShows);
    }
}
