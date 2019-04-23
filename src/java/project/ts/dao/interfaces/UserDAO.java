/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ts.dao.interfaces;

import java.util.List;
import project.ts.objects.User;

/**
 *
 * @author Rozma
 */
public interface UserDAO {
    
    void addUser(User user);
    
    User getUser(int idUser);
    
    List<User> getUsers();
    
    void updateUser(User uesr);
    
    void deleteUser(int idUser);
    
    User getUserByLogin(String login, String password);
}
