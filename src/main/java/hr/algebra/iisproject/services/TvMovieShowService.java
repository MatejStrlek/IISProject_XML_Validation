package hr.algebra.iisproject.services;

import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.repos.TvMovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TvMovieShowService {
    @Autowired
    private TvMovieShowRepository tvMovieShowRepository;

    @Transactional
    public void saveTvMovieShows(TvMovieShows tvMovieShows) {
        tvMovieShowRepository.saveAll(tvMovieShows.getTvMovieShows());
    }
}
