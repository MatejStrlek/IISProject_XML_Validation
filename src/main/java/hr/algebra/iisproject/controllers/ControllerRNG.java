package hr.algebra.iisproject.controllers;

import com.thaiopensource.validate.IncorrectSchemaException;
import hr.algebra.iisproject.services.TvMovieShowService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thaiopensource.relaxng.SchemaFactory;
import com.thaiopensource.validate.Schema;
import com.thaiopensource.validate.Validator;

import java.io.IOException;

@RestController
@RequestMapping("/api/rng")
public class ControllerRNG {
    TvMovieShowService tvMovieShowService;
    private Validator validator;

    public ControllerRNG(TvMovieShowService tvMovieShowService) {
        this.tvMovieShowService = tvMovieShowService;
    }
}
