package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.UserDetails;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FakeUserProvider implements UserDao {
    final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

    public FakeUserProvider() {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        UserDetails userDiego = new UserDetails();
        userDiego.setUsername("20907511M");
        userDiego.setPassword(passwordEncryptor.encryptPassword("1234"));
        knownUsers.put("20907511M", userDiego);

        UserDetails userJoan = new UserDetails();
        userJoan.setUsername("21234569S");
        userJoan.setPassword(passwordEncryptor.encryptPassword("1234"));
        knownUsers.put("21234569S", userJoan);
    }

    @Override
    public UserDetails loadUserByUsername(String username, String password) {
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

    @Override
    public Collection<UserDetails> listAllUsers() {
        return knownUsers.values();
    }
}