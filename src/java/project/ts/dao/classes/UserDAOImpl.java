/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ts.dao.classes;

import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.Car;
import project.ts.objects.User;

/**
 *
 * @author Rozma
 */
public class UserDAOImpl implements UserDAO {

    private void executeModifyQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Connection connection = DbUtil.getIstance().getConnection();
            connection.createStatement().execute(sql);
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private User wrapInUser(ResultSet resultSet) throws SQLException {
        User user = null;

        user = new User(
                resultSet.getInt("id_uzytkownik"),
                resultSet.getString("imie"),
                resultSet.getString("nazwisko"),
                resultSet.getString("login"),
                resultSet.getString("haslo")
        );

        return user;
    }

    private void preparedUser(PreparedStatement prepStat, User user) throws SQLException {

        prepStat.setString(1, user.getName());
        prepStat.setString(2, user.getSurname());
        prepStat.setString(3, user.getLogin());
        prepStat.setString(4, user.getPassword());

        prepStat.executeUpdate();
        prepStat.close();
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO uzytkownik(imie,nazwisko,login,haslo)values(?,?,?,?)";
        try {
            Connection connection = DbUtil.getIstance().getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedUser(prepStat, user);

        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUser(int idUser) {
        User user = null;
        String sql = "SELECT * FROM uzytkownik WHERE id_uzytkownik = '" + idUser + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                user = wrapInUser(resultSet);
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> listOfUsers = new ArrayList();
        String sql = "SELECT * FROM uzytkownik";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                listOfUsers.add(wrapInUser(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listOfUsers;
    }
    
    public User getUserByLogin(String login, String password){
        User user = null;
        String sql = "SELECT * FROM uzytkownik WHERE login = '" + login + "'" + " AND haslo = '" + password + "';" ;
        
        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                user = wrapInUser(resultSet);
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE uzytkownik SET imie = ?,nazwisko = ?,login = ?,haslo = ? WHERE id_uzytkownik = '" + user.getIdUser() + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedUser(prepStat, user);

        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteUser(int idUser) {
        String sql = "DELETE FROM uzytkownik WHERE id_uzytkownik = '" + idUser + "'";
        executeModifyQuery(sql);
    }

}
