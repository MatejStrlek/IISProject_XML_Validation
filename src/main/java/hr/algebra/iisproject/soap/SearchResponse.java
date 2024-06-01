package hr.algebra.iisproject.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "searchResponse", namespace = "http://algebra.hr/entities")
public class SearchResponse {
    private List<String> result = new ArrayList<>();

    @XmlElement(namespace = "http://algebra.hr/entities")
    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}

