package hr.algebra.iisproject.services;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.repos.TvMovieShowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TvMovieShowService {
    private static final Logger logger = LoggerFactory.getLogger(TvMovieShowService.class);

    @Autowired
    private TvMovieShowRepository tvMovieShowRepository;

    @Transactional
    public void saveTvMovieShows(TvMovieShows tvMovieShows) {
        if (tvMovieShows.getTvMovieShows().isEmpty()) {
            logger.error("Attempt to save empty or null list of TvMovieShows");
            throw new IllegalArgumentException("The list of TvMovieShows cannot or empty.");
        }

        logger.info("Saving list of TvMovieShows to the database, count: {}", tvMovieShows.getTvMovieShows().size());
        tvMovieShowRepository.saveAll(tvMovieShows.getTvMovieShows());
        logger.info("Successfully saved list of TvMovieShows to the database");
    }

    @Transactional
    public List<TvMovieShow> getAllTvMovieShows() {
        return tvMovieShowRepository.findAll();
    }

    //api
    public List<TvMovieShow> getAllShows() {
        return tvMovieShowRepository.findAll();
    }

    public Optional<TvMovieShow> getShowById(Long id) {
        return tvMovieShowRepository.findById(id);
    }

    public TvMovieShow createShow(TvMovieShow show) {
        return tvMovieShowRepository.save(show);
    }

    public Optional<TvMovieShow> updateShow(Long id, TvMovieShow showDetails) {
        return tvMovieShowRepository.findById(id).map(show -> {
            show.setTitle(showDetails.getTitle());
            show.setEpisodes(showDetails.getEpisodes());
            show.setYear(showDetails.getYear());
            show.setOriginalChannel(showDetails.getOriginalChannel());
            show.setAmericanCompany(showDetails.getAmericanCompany());
            show.setNote(showDetails.getNote());
            show.setTechnique(showDetails.getTechnique());
            show.setImdb(showDetails.getImdb());
            show.setGoogleUsers(showDetails.getGoogleUsers());
            return tvMovieShowRepository.save(show);
        });
    }

    public void deleteShow(Long id) {
        tvMovieShowRepository.deleteById(id);
    }
}
