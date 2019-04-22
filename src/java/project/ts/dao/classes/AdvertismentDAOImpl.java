/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ts.dao.classes;

import db.DbUtil;
import java.awt.Toolkit;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.Advertisment;
import project.ts.objects.User;

/**
 *
 * @author Rozma
 */
public class AdvertismentDAOImpl implements AdvertismentDAO{

    CarDAO cardao; 
    UserDAO userdao;

    public AdvertismentDAOImpl(CarDAO cardao, UserDAO userdao) {
        this.cardao = cardao;
        this.userdao = userdao;
    }
    
    
    private ResultSet executeFetchQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Connection connection = DbUtil.getConnection();
            resultSet = connection.createStatement().executeQuery(sql);
            //connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }
    
    private void executeModifyQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Connection connection = DbUtil.getConnection();
            connection.createStatement().execute(sql);
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

      private Advertisment wrapInAdvertisment (ResultSet resultSet) throws SQLException {
        Advertisment advertisment = null;

        advertisment = new Advertisment(
                resultSet.getInt("id_ogloszenie"),
                userdao.getUser(resultSet.getInt("uzytkownik")),
                cardao.getCar(resultSet.getInt("samochod")),
                resultSet.getInt("przebieg"),
                resultSet.getBoolean("uszkodzony"),
                resultSet.getString("VIN"),
                Toolkit.getDefaultToolkit().createImage(resultSet.getBlob("zdjecie").getBytes(1, (int)resultSet.getBlob("zdjecie").length())),
                resultSet.getString("opis")
        );
               
        return advertisment;
    }
      
      private void preparedAdvertisment(PreparedStatement prepStat, Advertisment advertisment) throws SQLException {

        prepStat.setInt(1, advertisment.getIdCar().getIdCar()); //idCar to po prostu obiekt CAR. User to samo
        prepStat.setInt(2, advertisment.getIdUser().getIdUser());
        prepStat.setInt(3,advertisment.getCarMileage() );
        prepStat.setBoolean(4, advertisment.isDemaged());
        prepStat.setString(5,advertisment.getVin());
        prepStat.setBlob(6, (Blob)advertisment.getImage());
        prepStat.setString(7,advertisment.getDescription());
        
        prepStat.executeUpdate();
        prepStat.close();
    }
      
      
    @Override
    public void addAdvertisment(Advertisment advertisment) {
            String sql = "INSERT INTO ogloszenie(uzytkownik,samochod,przebieg,uszkodzony,VIN,zdjecie,opis)values(?,?,?,?,?,?,?)";
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedAdvertisment(prepStat, advertisment);

        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public Advertisment getAdvertisment(int idAdvertisment) {
       Advertisment advertisment = null;
        String sql = "SELECT * FROM ogloszenie WHERE id_ogloszenie = '" + idAdvertisment + "';";
        ResultSet resultSet = executeFetchQuery(sql);
        try {
            while (resultSet.next()) {
                advertisment = wrapInAdvertisment(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return advertisment;
    }

    @Override
    public List<Advertisment> getAdvertisments() {
      List<Advertisment> listOfAdvertisments = new ArrayList();
      String sql = "SELECT * FROM ogloszenie";
      ResultSet resultSet = executeFetchQuery(sql);
        try {
            while (resultSet.next()) {
                listOfAdvertisments.add(wrapInAdvertisment(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
      return listOfAdvertisments; 
    }
    

    @Override
    public void updateAdvertisment(Advertisment advertisment) {
          String sql = "UPDATE ogloszenie SET samochod = ?,uzytkownik = ?,przebieg = ?,uszkodzony = ?, VIN = ?, zdjecie = ?,opis = ? WHERE id_ogloszenie = '" + advertisment.getIdAdvertisment() + "';";

        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedAdvertisment(prepStat, advertisment);

        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAdvertisment(int idAdvertisment) {
       String sql = "DELETE FROM ogloszenie WHERE id_ogloszenie = '" + idAdvertisment + "'";
        executeModifyQuery(sql);
    }
    
}
