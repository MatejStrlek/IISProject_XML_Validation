package utils;

import hr.algebra.iisproject.models.TvMovieShow;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class XmlUtil {
    public static TvMovieShow parseXmlToEntity(InputStream xmlStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TvMovieShow.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (TvMovieShow) unmarshaller.unmarshal(xmlStream);
    }
}
