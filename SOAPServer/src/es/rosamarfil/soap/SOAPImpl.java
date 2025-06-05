package es.rosamarfil.soap;

import es.rosamarfil.model.User;
import java.util.List;
import javax.jws.WebService;

@WebService(endpointInterface = "es.rosamarfil.soap.SOAPI")
public class SOAPImpl implements SOAPI{
     @Override
    public List<User> getUsers() {
        return User.getUsers();
    }

    @Override
    public void addUser(User user) {
        User.getUsers().add(user);
    }
}
