package hr.algebra.iisproject.models;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TvMovieShows")
@Getter
@Setter
public class TvMovieShows {
    @XmlElement(name = "TvMovieShow")
    private List<TvMovieShow> tvMovieShows;
}
