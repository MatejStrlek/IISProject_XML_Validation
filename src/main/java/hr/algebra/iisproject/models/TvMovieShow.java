package hr.algebra.iisproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Entity
@Table(name = "tv_movie_shows")
public class TvMovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement(name = "Title")
    @Column(name = "title")
    private String title;
    @XmlElement(name = "Episodes")
    @Column(name = "episodes")
    private int episodes;
    @XmlElement(name = "Year")
    @Column(name = "year")
    private String year;
    @XmlElement(name = "OriginalChannel")
    @Column(name = "original_channel")
    private String originalChannel;
    @XmlElement(name = "AmericanCompany")
    @Column(name = "american_company")
    private String americanCompany;
    @XmlElement(name = "Note")
    @Column(name = "note")
    private String note;
    @XmlElement(name = "Technique")
    @Column(name = "technique")
    private String technique;
    @XmlElement(name = "IMDb")
    @Column(name = "imdb")
    private double imdb;
    @XmlElement(name = "GoogleUsers")
    @Column(name = "google_users")
    private String googleUsers;

}
