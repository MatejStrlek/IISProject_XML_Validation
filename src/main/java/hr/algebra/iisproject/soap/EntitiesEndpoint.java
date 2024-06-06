package hr.algebra.iisproject.soap;

import hr.algebra.iisproject.models.TvMovieShow;
import hr.algebra.iisproject.models.TvMovieShows;
import hr.algebra.iisproject.services.TvMovieShowService;
import jakarta.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import hr.algebra.iisproject.utils.XmlUtils;

import java.io.File;
import java.util.List;

@Endpoint
@WebService
public class EntitiesEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(EntitiesEndpoint.class);
    private static final String NAMESPACE_URI = "http://iislocal.gg/entities";
    private final TvMovieShowService tvMovieShowService;

    public EntitiesEndpoint(TvMovieShowService tvMovieShowService) {
        this.tvMovieShowService = tvMovieShowService;
    }

    public EntitiesEndpoint() {
        this.tvMovieShowService = null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchRequest")
    @ResponsePayload
    public SearchResponse searchEntities(@RequestPayload SearchRequest request) throws Exception {
        logger.info("Received search request with term: {}", request.getTerm());
        String searchTerm = request.getTerm();

        // Generate XML file with entity information
        File xmlFile = generateXmlFileWithEntities();

        // Search the XML file using XPath
        List<String> results = XmlUtils.searchXmlFile(xmlFile, searchTerm);

        // Prepare the response
        SearchResponse response = new SearchResponse();
        response.getResult().addAll(results);

        logger.info("Search response prepared with results: {}", results);
        return response;
    }

    private File generateXmlFileWithEntities() throws Exception {
        List<TvMovieShow> shows = tvMovieShowService.getAllTvMovieShows();
        TvMovieShows tvMovieShows = new TvMovieShows();
        tvMovieShows.setTvMovieShows(shows);

        File xmlFile = File.createTempFile("entities", ".xml");
        xmlFile.deleteOnExit();

        JAXBContext context = JAXBContext.newInstance(TvMovieShows.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(tvMovieShows, xmlFile);

        logger.info("Generated XML file with entities");

        return xmlFile;
    }
}

