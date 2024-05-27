package hr.algebra.iisproject.RpcServer;

import hr.algebra.iisproject.services.RpcWeatherService;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class XmlRpcServerRunner implements CommandLineRunner {

    private final RpcWeatherService weatherService;

    public XmlRpcServerRunner(RpcWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void run(String... args) throws Exception {
        WebServer webServer = new WebServer(8081);

        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.addHandler("RpcWeatherService", weatherService.getClass());
        xmlRpcServer.setHandlerMapping(phm);

        webServer.start();
        System.out.println("XML-RPC server started successfully on port 8081.");
    }
}

