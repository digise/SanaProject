package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.UserDetails;

import java.util.Collection;

public interface UserDao {
    UserDetails loadUserByUsername(String username, String password);
    Collection<UserDetails> listAllUsers();
}