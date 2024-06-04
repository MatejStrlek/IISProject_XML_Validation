package hr.algebra.iisproject.utils;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.models.TvMovieShows;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> searchXmlFile(File xmlFile, String searchTerm) throws Exception {
        List<String> results = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        XPathExpression expr = xpath.compile("//TvMovieShow[contains(Title, '" + searchTerm + "')]");

        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            results.add(nodes.item(i).getTextContent());
        }
        return results;
    }
}
