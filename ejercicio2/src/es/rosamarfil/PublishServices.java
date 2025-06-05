package es.rosamarfil;

import javax.xml.ws.Endpoint;
import es.rosamarfil.soap.SOAPImpl;

public class PublishServices {
    public static void main(String[] args) {
        System.out.println("Iniciando servicio web SOAP...");
        Endpoint.publish("http://localhost:1516/WS/Users", new SOAPImpl());
        System.out.println("Servicio web disponible en: http://localhost:1516/WS/Users");
        System.out.println("WSDL disponible en: http://localhost:1516/WS/Users?wsdl");
        System.out.println("Presiona Ctrl+C para detener el servicio...");
    }
}