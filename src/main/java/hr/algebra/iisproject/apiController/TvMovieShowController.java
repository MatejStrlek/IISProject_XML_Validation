package hr.algebra.iisproject.apiController;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.services.TvMovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class TvMovieShowController {
    @Autowired
    private TvMovieShowService tvMovieShowService;

    @GetMapping
    public ResponseEntity<List<TvMovieShow>> getAllShows() {
        return ResponseEntity.ok(tvMovieShowService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvMovieShow> getShowById(@PathVariable Long id) {
        return tvMovieShowService.getShowById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TvMovieShow> createShow(@RequestBody TvMovieShow show) {
        return ResponseEntity.ok(tvMovieShowService.createShow(show));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvMovieShow> updateShow(@PathVariable Long id, @RequestBody TvMovieShow showDetails) {
        return tvMovieShowService.updateShow(id, showDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        tvMovieShowService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}

