package es.rosamarfil.client;

import es.rosamarfil.soap.*;
import java.util.List;

public class UserClient {
    public static void main(String[] args) {
        try {
            // Instanciar el servicio
            SOAPImplService service = new SOAPImplService();
            SOAPI userService = service.getSOAPImplPort();

            // Mostrar lista de usuarios
            System.out.println("Usuarios actuales:");
            List<User> usuarios = userService.getUsers();
            for (User u : usuarios) {
                System.out.println("Nombre: " + u.getName() + ", Username: " + u.getUsername());
            }

            // Agregar un nuevo usuario
            User nuevo = new User();
            nuevo.setName("Pablo");
            nuevo.setUsername("Ruiz");
            userService.addUser(nuevo);

            // Ver lista actualizada
            System.out.println("\nUsuarios actualizados:");
            usuarios = userService.getUsers();
            for (User u : usuarios) {
                System.out.println("Nombre: " + u.getName() + ", Username: " + u.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


