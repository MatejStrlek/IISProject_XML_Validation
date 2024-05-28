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
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RpcWeatherService {

    private static final String DHMZ_URL = "https://vrijeme.hr/hrvatska_n.xml";

    public String getTemperatureByCity(String cityName) {
        List<String> temperatures = new ArrayList<>();

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

            String normalizedCityName = normalizeString(cityName);
            Pattern pattern = Pattern.compile(Pattern.quote(normalizedCityName), Pattern.CASE_INSENSITIVE);

            for (int i = 0; i < cityList.getLength(); i++) {
                Node cityNode = cityList.item(i);

                if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element cityElement = (Element) cityNode;
                    String name = cityElement.getElementsByTagName("GradIme").item(0).getTextContent();

                    String normalizedName = normalizeString(name);

                    Matcher matcher = pattern.matcher(normalizedName);
                    if (matcher.find()) {
                        Element podatciElement = (Element) cityElement.getElementsByTagName("Podatci").item(0);
                        String temperature = podatciElement.getElementsByTagName("Temp").item(0).getTextContent().trim();
                        temperatures.add(name + ": " + temperature + "°C");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (temperatures.isEmpty()) {
            return "City not found or unable to fetch data";
        } else {
            return String.join(", ", temperatures);
        }


    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}
