package hr.algebra.iisproject.soap;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "searchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse {
    // Getters and Setters
    @XmlElement(name = "result")
    private List<String> result = new ArrayList<>();
}