package hr.algebra.iisproject.services;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RpcWeatherService {

    private static final String DHMZ_URL = "https://vrijeme.hr/hrvatska_n.xml";

    public String getTemperatureByCity(String cityName) {
        try {
            URL url = new URL(DHMZ_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream responseStream = connection.getInputStream();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(responseStream);
            doc.getDocumentElement().normalize();

            NodeList cityList = doc.getElementsByTagName("Grad");

            for (int i = 0; i < cityList.getLength(); i++) {
                Node cityNode = cityList.item(i);

                if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element cityElement = (Element) cityNode;
                    String name = cityElement.getElementsByTagName("GradIme").item(0).getTextContent();

                    if (name.equalsIgnoreCase(cityName)) {
                        Element podatciElement = (Element) cityElement.getElementsByTagName("Podatci").item(0);
                        return podatciElement.getElementsByTagName("Temp").item(0).getTextContent().trim();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "City not found or unable to fetch data";
    }
}
