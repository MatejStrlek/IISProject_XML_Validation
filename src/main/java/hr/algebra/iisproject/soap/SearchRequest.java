package hr.algebra.iisproject.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "searchRequest", namespace = "http://algebra.hr/entities")
public class SearchRequest {
    private String term;

    @XmlElement(namespace = "http://algebra.hr/entities")
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}

