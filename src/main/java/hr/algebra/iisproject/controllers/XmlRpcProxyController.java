package hr.algebra.iisproject.controllers;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rpc")
public class XmlRpcProxyController {

    @PostMapping
    public ResponseEntity<?> handleRpcRequest(@RequestBody Map<String, Object> request) {
        try {
            String methodName = (String) request.get("methodName");
            Object[] params = ((List<?>) request.get("params")).toArray();

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8081/xmlrpc"));

            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Object result = client.execute(methodName, params);

            return ResponseEntity.ok(Map.of("result", result, "id", request.get("id"), "jsonrpc", "2.0"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", Map.of("message", e.getMessage()),
                    "id", request.get("id"),
                    "jsonrpc", "2.0"
            ));
        }
    }
}

