package es.uji.ei102720gmtp.SanaProject.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;
import es.uji.ei102720gmtp.SanaProject.model.UserDetails;

@Repository
public class FakeUser extends UserDao {
    final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

    public FakeUser() {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        UserDetails userResponsable = new UserDetails();
        userResponsable.setPassword("87346182H");
        userResponsable.setPassword(passwordEncryptor.encryptPassword("647"));
        knownUsers.put("responsable", userResponsable);
    }

    @Override
    public UserDetails loadUserByNif(String username, String password) {
        UserDetails user = knownUsers.get(username.trim());
        if (user == null)
            return null; // Usuari no trobat
        // Contrasenya
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            // Es deuria esborrar de manera segura el camp password abans de tornar-lo
            return user;
        }
        else {
            return null; // bad login!
        }
    }
}
