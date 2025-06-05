package es.rosamarfil;
import es.rosamarfil.soap.SOAPImpl;
import javax.xml.ws.Endpoint;

public class PublishServices {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1516/WS/Users", new SOAPImpl());
        System.out.println("Servicio publicado en http://localhost:1516/WS/Users?wsdl");
    }
}
