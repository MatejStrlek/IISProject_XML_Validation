package hr.algebra.iisproject.soap;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "searchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class SearchRequest {
    @XmlElement(name = "term")
    private String term;
}