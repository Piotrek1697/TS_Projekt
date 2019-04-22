/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ts.dao.classes;

import db.DbUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.Advertisment;

/**
 *
 * @author Rozma
 */
public class AdvertismentDAOImpl implements AdvertismentDAO {

    private CarDAO carDao;
    private UserDAO userDao;

    public AdvertismentDAOImpl(CarDAO cardao, UserDAO userdao) {
        this.carDao = cardao;
        this.userDao = userdao;
    }

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

    private Advertisment wrapInAdvertisment(ResultSet resultSet) throws SQLException, IOException {
        Advertisment advertisment = null;

        advertisment = new Advertisment(
                resultSet.getInt("id_ogloszenie"),
                userDao.getUser(resultSet.getInt("uzytkownik")),
                carDao.getCar(resultSet.getInt("samochod")),
                resultSet.getInt("przebieg"),
                resultSet.getBoolean("uszkodzony"),
                resultSet.getString("VIN"),
                ImageIO.read(resultSet.getBlob("zdjecie").getBinaryStream()),
                resultSet.getString("opis"),
                resultSet.getDouble("cena")
        );

        return advertisment;
    }

    private void preparedAdvertisment(PreparedStatement prepStat, Advertisment advertisment) throws SQLException, IOException {

        prepStat.setInt(1, advertisment.getIdCar().getIdCar()); //idCar to po prostu obiekt CAR. User to samo
        prepStat.setInt(2, advertisment.getIdUser().getIdUser());
        prepStat.setInt(3, advertisment.getCarMileage());
        prepStat.setBoolean(4, advertisment.isDemaged());
        prepStat.setString(5, advertisment.getVin());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(advertisment.getImage(), "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        prepStat.setBlob(6, is);
        prepStat.setString(7, advertisment.getDescription());
        prepStat.setDouble(8, advertisment.getPrice());

        prepStat.executeUpdate();
        prepStat.close();
    }

    @Override
    public void addAdvertisment(Advertisment advertisment) {
        String sql = "INSERT INTO ogloszenie(uzytkownik,samochod,przebieg,uszkodzony,VIN,zdjecie,opis,cena)values(?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DbUtil.getIstance().getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedAdvertisment(prepStat, advertisment);

        } catch (SQLException | IOException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Advertisment getAdvertisment(int idAdvertisment) {
        Advertisment advertisment = null;
        String sql = "SELECT * FROM ogloszenie WHERE id_ogloszenie = '" + idAdvertisment + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                advertisment = wrapInAdvertisment(resultSet);
            }
            connection.close();
            resultSet.close();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        return advertisment;
    }

    @Override
    public List<Advertisment> getAdvertisments() {
        List<Advertisment> listOfAdvertisments = new ArrayList();
        String sql = "SELECT * FROM ogloszenie";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfAdvertisments.add(wrapInAdvertisment(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        return listOfAdvertisments;
    }

    @Override
    public void updateAdvertisment(Advertisment advertisment) {
        String sql = "UPDATE ogloszenie SET samochod = ?,uzytkownik = ?,przebieg = ?,uszkodzony = ?, VIN = ?, zdjecie = ?, opis = ?, cena = ? WHERE id_ogloszenie = '" + advertisment.getIdAdvertisment() + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedAdvertisment(prepStat, advertisment);

        } catch (SQLException | IOException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAdvertisment(int idAdvertisment) {
        String sql = "DELETE FROM ogloszenie WHERE id_ogloszenie = '" + idAdvertisment + "'";
        executeModifyQuery(sql);
    }

}
