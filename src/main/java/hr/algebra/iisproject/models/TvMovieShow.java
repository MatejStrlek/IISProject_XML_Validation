package hr.algebra.iisproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class TvMovieShow {
    @XmlElement(name = "Title")
    private String title;
    @XmlElement(name = "Episodes")
    private int episodes;
    @XmlElement(name = "Year")
    private String year;
    @XmlElement(name = "OriginalChannel")
    private String originalChannel;
    @XmlElement(name = "AmericanCompany")
    private String americanCompany;
    @XmlElement(name = "Note")
    private String note;
    @XmlElement(name = "Technique")
    private String technique;
    @XmlElement(name = "IMDb")
    private double imdb;
    @XmlElement(name = "GoogleUsers")
    private String googleUsers;

}
