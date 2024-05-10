package hr.algebra.iisproject.services;

import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.repos.TvMovieShowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
