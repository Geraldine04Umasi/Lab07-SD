package es.rosamarfil.soap;

import es.rosamarfil.model.User;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPI {
     @WebMethod
    List<User> getUsers();

    @WebMethod
    void addUser(User user);
}
