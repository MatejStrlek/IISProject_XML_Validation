package hr.algebra.iisproject.services;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.repos.TvMovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvMovieShowService {
    @Autowired
    private TvMovieShowRepository tvMovieShowRepository;

    public void saveTvMovieShow(TvMovieShow tvMovieShow) {
        tvMovieShowRepository.save(tvMovieShow);
    }
}
