package project.ts.dao.interfaces;

import project.ts.objects.Car;
import java.util.List;


/**
 *
 * @author Rozma
 */
public interface CarDAO {
    
    void addCar(Car car);
    
    Car getCar(int idCar);
    
    List<Car> getCars();
    
    void updateCar(Car car);
    
    void deleteCar(int idCar);
    
    List<Car> getBrandCars(String brand);
    
    List<Car> getBrandModel(String brand, String model);
    
}
