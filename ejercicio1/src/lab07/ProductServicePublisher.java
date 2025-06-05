package lab07;

import javax.xml.ws.Endpoint;

public class ProductServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1516/WS/Lab07", new ProductSOAPService());
        System.out.println("Servicio SOAP publicado en http://localhost:1516/WS/Lab07");
        System.out.println("WSDL disponible en: http://localhost:1516/WS/Lab07?wsdl");
        System.out.println("Presiona Ctrl+C para detener el servicio...");
        
        // Mantener el servicio corriendo
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("Servicio detenido");
        }
    }
}