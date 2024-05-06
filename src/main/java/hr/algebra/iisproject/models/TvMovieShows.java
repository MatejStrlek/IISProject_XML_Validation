package hr.algebra.iisproject.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TvMovieShows")
@XmlAccessorType(XmlAccessType.FIELD)
public class TvMovieShows {
    @XmlElement(name = "TvMovieShow")
    private List<TvMovieShow> tvMovieShows;

    public List<TvMovieShow> getTvMovieShows() {
        return tvMovieShows;
    }

    public void setTvMovieShows(List<TvMovieShow> tvMovieShows) {
        this.tvMovieShows = tvMovieShows;
    }
}
