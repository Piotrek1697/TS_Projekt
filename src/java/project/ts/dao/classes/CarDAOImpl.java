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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.ts.dao.interfaces.CarDAO;
import project.ts.objects.Car;

/**
 *
 * @author Rozma
 */
public class CarDAOImpl implements CarDAO {

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

    @Override
    public void addCar(Car car) {
        String sql = "INSERT INTO SAMOCHOD (marka,model,rocznik,rodzaj_paliwa,poj_silnika,konie_mechaniczne,ilosc_drzwi)values(?,?,?,?,?,?,?)";
        try {
            Connection connection = DbUtil.getIstance().getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedCar(prepStat, car);

        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preparedCar(PreparedStatement prepStat, Car car) throws SQLException {

        prepStat.setString(1, car.getBrand());
        prepStat.setString(2, car.getModel());
        prepStat.setInt(3, car.getYearOfProduction());
        prepStat.setString(4, car.getFuelType());
        prepStat.setDouble(5, car.getEngineCapacity());
        prepStat.setInt(6, car.getHorsepower());
        prepStat.setInt(7, car.getDoors());

        prepStat.executeUpdate();
        prepStat.close();

    }

    private Car wrapInCar(ResultSet resultSet) throws SQLException {
        Car car = null;

        car = new Car(
                resultSet.getInt("id_samochod"),
                resultSet.getString("marka"),
                resultSet.getString("model"),
                resultSet.getInt("rocznik"),
                resultSet.getString("rodzaj_paliwa"),
                resultSet.getDouble("poj_silnika"),
                resultSet.getInt("konie_mechaniczne"),
                resultSet.getInt("ilosc_drzwi")
        );

        return car;
    }

    @Override
    public Car getCar(int idCar) {
        Car car = null;
        String sql = "SELECT * FROM SAMOCHOD WHERE id_samochod = '" + idCar + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                car = wrapInCar(resultSet);
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return car;
    }

    @Override
    public List<Car> getCars() {
        List<Car> listOfCars = new ArrayList();
        String sql = "SELECT *FROM SAMOCHOD";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfCars.add(wrapInCar(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfCars;
    }

    @Override
    public void updateCar(Car car) {

        String sql = "UPDATE samochod SET marka = ?,model = ?,rocznik = ?,rodzaj_paliwa = ?, poj_silnika = ?,konie_mechaniczne = ?,ilosc_drzwi = ? WHERE id_samochod = '" + car.getIdCar() + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedCar(prepStat, car);

        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteCar(int idCar) {
        String sql = "DELETE FROM samochod WHERE id_samochod = '" + idCar + "'";
        executeModifyQuery(sql);
    }

    @Override
    public List<Car> getBrandCars(String brand) {
        List<Car> listOfBrandCars = new ArrayList();
        String sql = "select * from samochod where marka = '" + brand + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfBrandCars.add(wrapInCar(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfBrandCars;
    }

    @Override
    public List<Car> getBrandModel(String brand, String model) {
        List<Car> listOfBrandModel = new ArrayList();
        String sql = "select * from samochod where marka = '" + brand + "' AND model = '" + model + "';";

        try {
            Connection connection = DbUtil.getIstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                listOfBrandModel.add(wrapInCar(resultSet));
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOfBrandModel;
    }
}
