package es.rosamarfil.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            System.out.println("=== PROBANDO SERVICIO SOAP ===");
            
            // 1. Obtener usuarios
            System.out.println("\n1. Obteniendo lista de usuarios...");
            getUsersRequest();
            
            // 2. Agregar un usuario
            System.out.println("\n2. Agregando nuevo usuario (Pablo Ruiz)...");
            addUserRequest();
            
            // 3. Obtener usuarios nuevamente
            System.out.println("\n3. Obteniendo lista actualizada de usuarios...");
            getUsersRequest();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void getUsersRequest() throws Exception {
        String soapRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "<soap:Body>" +
            "<ns2:getUsers xmlns:ns2=\"http://soap.rosamarfil.es/\"/>" +
            "</soap:Body>" +
            "</soap:Envelope>";
        
        String response = sendSoapRequest(soapRequest);
        parseUsersResponse(response);
    }
    
    private static void addUserRequest() throws Exception {
        String soapRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "<soap:Body>" +
            "<ns2:addUser xmlns:ns2=\"http://soap.rosamarfil.es/\">" +
            "<arg0><name>Pablo</name><username>Ruiz</username></arg0>" +
            "</ns2:addUser>" +
            "</soap:Body>" +
            "</soap:Envelope>";
        
        String response = sendSoapRequest(soapRequest);
        System.out.println("Usuario agregado exitosamente.");
    }
    
    private static String sendSoapRequest(String soapRequest) throws Exception {
        URL url = new URL("http://localhost:1516/WS/Users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("SOAPAction", "");
        
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(soapRequest.getBytes());
        outputStream.flush();
        outputStream.close();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        
        return response.toString();
    }
    
    private static void parseUsersResponse(String response) {
        System.out.println("Usuarios encontrados:");
        // Extraer usuarios de la respuesta XML
        String[] users = response.split("<return>");
        for (int i = 1; i < users.length; i++) {
            String userXml = users[i];
            String name = extractValue(userXml, "name");
            String username = extractValue(userXml, "username");
            System.out.println("  - " + name + " (" + username + ")");
        }
    }
    
    private static String extractValue(String xml, String tag) {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        int start = xml.indexOf(startTag) + startTag.length();
        int end = xml.indexOf(endTag);
        return xml.substring(start, end);
    }
}