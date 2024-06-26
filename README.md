# Backend XML Validations and Retrieving
This project implements a backend system with multiple functionalities involving REST APIs, SOAP services, XML validation, and integration with public APIs. It supports various methods for processing and validating XML files and provides services to fetch and search for entities using XML data.

## Features
### REST API with XML and XSD Validation
A POST endpoint to save a new entity by sending an XML file, which is validated using an XSD schema.

### REST API with XML and RNG Validation
A POST endpoint to save a new entity by sending an XML file, which is validated using an RNG schema.

### SOAP API for Entity Search
A SOAP service that receives a search term, generates an XML file containing all entities, and searches the file using XPath expressions to return results.

### XML Validation with JAXB
Validate the generated XML file from the SOAP service against a predefined XSD schema using JAXB.

### XML-RPC Server for Weather Data
An XML-RPC server application that retrieves the current temperature for a given city from the DHMZ weather service.

### REST API Integration with Security
Integrate with a public REST API using security keys, or implement a custom REST API with JWT token-based security.

### Client Application
Develop a client desktop or web application (Java or C#) to allow users to invoke the services from the above steps.

## Technologies Used
- Spring Boot
- Spring MVC
- Spring Security (for JWT)
- JAXB
- XSD/RNG validation
- SOAP
- XML-RPC
- XPath
- Public REST APIs
- Java client application
