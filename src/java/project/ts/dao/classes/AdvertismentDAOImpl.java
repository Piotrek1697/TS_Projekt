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
import project.ts.objects.Car;
import project.ts.objects.User;

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

        prepStat.setInt(1, advertisment.getIdUser().getIdUser());
        prepStat.setInt(2, advertisment.getIdCar().getIdCar()); 
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
    public Advertisment getAdvertisment(Advertisment advertisment) {
        String sql = "select * from komis_samochodowy.ogloszenie where samochod = '"+ advertisment.getIdCar().getIdCar()+"' and uzytkownik ='"+ advertisment.getIdUser().getIdUser() +"' and vin = '"+ advertisment.getVin()+"';";

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
    public Advertisment getAdvertisment(int idAdvert) {
        String sql = "select * from komis_samochodowy.ogloszenie where id_ogloszenie = '"+ idAdvert +"';";
        Advertisment advertisment = null;
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
        String sql = "UPDATE ogloszenie SET uzytkownik = ?,samochod = ?,przebieg = ?,uszkodzony = ?, VIN = ?, zdjecie = ?, opis = ?, cena = ? WHERE id_ogloszenie = '" + advertisment.getIdAdvertisment() + "';";

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

    @Override
    public List<Advertisment> getBrandAdvertisment(String brand) {
        List<Advertisment> listOfBrandAdvertisment = new ArrayList();
        String sql = "SELECT id_ogloszenie,uzytkownik,samochod,przebieg,uszkodzony,VIN,zdjecie,opis,cena FROM komis_samochodowy.ogloszenie INNER JOIN komis_samochodowy.samochod ON komis_samochodowy.ogloszenie.samochod = komis_samochodowy.samochod.id_samochod where marka= '" + brand + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfBrandAdvertisment.add(wrapInAdvertisment(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(AdvertismentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfBrandAdvertisment;
    }

    @Override
    public List<Advertisment> getBrandModalAdvertisment(String brand, String model) {
        List<Advertisment> listOfBrandModelAdvertisment = new ArrayList();
        String sql = "SELECT id_ogloszenie,uzytkownik,samochod,przebieg,uszkodzony,VIN,zdjecie,opis,cena FROM ogloszenie INNER JOIN samochod ON ogloszenie.samochod = samochod.id_samochod where marka = '" + brand + "' AND model = '" + model + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfBrandModelAdvertisment.add(wrapInAdvertisment(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(AdvertismentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfBrandModelAdvertisment;
    }

    @Override
    public List<Advertisment> getUserAdverts(int idUser) {
        List<Advertisment> listOfBrandModelAdvertisment = new ArrayList();
        String sql = "SELECT id_ogloszenie,uzytkownik,samochod,przebieg,uszkodzony,VIN,zdjecie,opis,cena from komis_samochodowy.ogloszenie inner join komis_samochodowy.uzytkownik on komis_samochodowy.uzytkownik.id_uzytkownik = komis_samochodowy.ogloszenie.uzytkownik where komis_samochodowy.uzytkownik.id_uzytkownik = '"+idUser+"';";
        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfBrandModelAdvertisment.add(wrapInAdvertisment(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(AdvertismentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfBrandModelAdvertisment;
    }
}
