
import db.DbUtil;
import project.ts.dao.classes.CarDAOImpl;
import project.ts.dao.interfaces.CarDAO;
import project.ts.objects.Car;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rozma
 */
public class testMain {
    
    public static void main(String[] args) {
        
        Car car1 = new Car(1, "BMW", "X5", 2015, "petrol", 2998.5, 263, 5);
        Car car2 = new Car(2, "BMW", "X6", 2015, "petrol", 2998.5, 363, 5);
        DbUtil db = new DbUtil();
        CarDAO cardao = new CarDAOImpl();
        //cardao.addCar(car1);
        System.out.println("Print: " + cardao.getCar(2).toString());
        
        //for(Car car : cardao.getCars()){
        //System.out.println("Print Array " + car.toString()); 
        //}
        
        //cardao.updateCar(car2);
        //System.out.println("Print UPDATE: " + cardao.getCar(2).toString());
        //cardao.deleteCar(3);
        System.out.println("Only BMW" + cardao.getBrandCars("BMW"));
        System.out.println("Only BMW X5" + cardao.getBrandModel("BMW","X5"));
    }
            
    
    
    
}
