package utils;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.models.TvMovieShows;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

public class XmlUtils {
    public static TvMovieShows parseXmlToEntity(InputStream xmlStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TvMovieShows.class, TvMovieShow.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (TvMovieShows) unmarshaller.unmarshal(xmlStream);
    }

    public static Validator initXSDValidator(InputStream inputStream) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(inputStream);
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }
}
