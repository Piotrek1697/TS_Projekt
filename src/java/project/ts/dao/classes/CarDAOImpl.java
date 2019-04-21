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

   private ResultSet executeFetchQuery(String sql){
       ResultSet resultSet = null;
       try{
       Connection connection = DbUtil.getConnection();
       resultSet = connection.createStatement().executeQuery(sql);
       connection.close();
       }catch (Exception ex){
           System.out.println(ex.getMessage());
       }
       return resultSet;
   }
   
   
   private void executeModifyQuery(String sql){
       ResultSet resultSet = null;
       try{
       Connection connection = DbUtil.getConnection();
       connection.createStatement().execute(sql);
       connection.close();
       }catch (Exception ex){
           System.out.println(ex.getMessage());
       }
    }
    
    
    @Override
    public void addCar(Car car) {
        String sql = "INSERT INTO SAMOCHOD (marka,model,rocznik,rodzaj_paliwa,poj_silnika,konie_mechaniczne,ilosc_drzwi)values(?,?,?,?,?,?,?)";
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement prepStat = connection.prepareStatement(sql);
            preparedCar(prepStat,car);
            
        } catch (SQLException ex) {
            Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void preparedCar(PreparedStatement prepStat,Car car) throws SQLException{
            prepStat.setString(1, car.getBrand());
            prepStat.setString(2, car.getModel());
            prepStat.setInt(3, car.getYearOfProduction());
            prepStat.setString(4, car.getFuelType());
            prepStat.setDouble(5,car.getEngineCapacity());
            prepStat.setInt(6,car.getHorsepower());
            prepStat.setInt(7, car.getDoors());
            prepStat.executeUpdate();
            prepStat.close();
        
    }

    private Car  wrapInCar(ResultSet resultSet) throws SQLException{
        Car car = new Car();
        while (resultSet.next()){
                
                  car.setBrand(resultSet.getString("marka"));
                  car.setModel(resultSet.getString("model"));
                  car.setYearOfProduction(resultSet.getInt("rocznik"));
                  car.setFuelType(resultSet.getString("rodzaj_paliwa"));
                  car.setEngineCapacity(resultSet.getDouble("poj_silnika"));
                  car.setHorsepower(resultSet.getInt("konie_mechaniczne"));
                  car.setDoors(resultSet.getInt("ilosc_drzwi"));
                                 
              }
        return car;
    }
    
    
    @Override
    public Car getCar(int idCar) {
         Car car = new Car();
         String sql = "SELECT * FROM SAMOCHOD WHERE id_samochod = '" + idCar + "'";
         ResultSet resultSet = executeFetchQuery(sql);
          try {
            car = wrapInCar(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
         
          return car;
    }

    @Override
    public List<Car> getCars() {
        List<Car> listOfCars = new ArrayList();
        String sql = "SELECT *FROM SAMOCHOD";
        ResultSet resultSet = executeFetchQuery(sql); 
        try {
            listOfCars.add(wrapInCar(resultSet));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
        return listOfCars;
    }

    @Override
    public void updateCar(Car car) {
        
        String sql = "UPDATE samochod SET (marka,model,rocznik,rodzaj_paliwa,poj_silnika,konie_mechaniczne,ilosc_drzwi)values(?,?,?,?,?,?,?)";

        try { 
           Connection connection = DbUtil.getConnection();
           PreparedStatement prepStat = connection.prepareStatement(sql);
           preparedCar(prepStat,car);
           
       } catch (SQLException ex) {
           Logger.getLogger(CarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    }

    @Override
    public void deleteCar(Car car) {
       String sql = "DELETE FROM CAR WHERE id_samochod = '" + car.getIdCar() + "'";
       executeModifyQuery(sql);
    }
    }

