
import util.LoadImage;
import db.DbUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import project.ts.dao.classes.AdvertismentDAOImpl;
import project.ts.dao.classes.CarDAOImpl;
import project.ts.dao.classes.UserDAOImpl;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.Advertisment;
import project.ts.objects.Car;
import project.ts.objects.User;

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
        User user1 = new User(1,"Adam","Rozmarynowski","Rozmek320","jamniczek");
        User user2 = new User(2,"Piotr","Janus","Pjanus420","audir8");
        User user3 = new User(4,"Piotr","Janus","Pjanus420","audir8v10"); 
        Car car1 = new Car(1, "BMW", "X5", 2015, "petrol", 2998.5, 263, 5);
        Car car2 = new Car(2, "BMW", "X6", 2015, "petrol", 2998.5, 363, 5);
        CarDAO cardao = new CarDAOImpl();
        UserDAO userdao = new UserDAOImpl();
        AdvertismentDAO addao = new AdvertismentDAOImpl(cardao,userdao);
        
        LoadImage loadImage = new LoadImage();
        BufferedImage bmw = null;
       
        try {
            bmw = ImageIO.read(new File("C:\\Users\\Rozma\\Documents\\GitHub\\TS_Projekt\\src\\java\\bmwx5.jpg"));
            bmw = loadImage.scaleImage(bmw);
                    } catch (IOException ex) {
            Logger.getLogger(testMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Advertisment advertisment1 = new Advertisment(1,user1,car1,50005,false,"437984738",bmw,null,3000);
        Advertisment advertisment2 = new Advertisment(2,user3,car2,150005,true,"432294738",null,null,2000);
        addao.addAdvertisment(advertisment1);
        //addao.deleteAdvertisment(3);
        //addao.getAdvertisments();
        //System.out.println(userdao.getUsers());
        //userdao.addUser(user2);
        
        //System.out.println("User: " + userdao.getUser(2).toString());
        //userdao.updateUser(user3);
        //System.out.println(userdao.getUsers().toString());
          
        //cardao.addCar(car1);
        //System.out.println("Print: " + cardao.getCar(2).toString());
        
        //for(Car car : cardao.getCars()){
        //System.out.println("Print Array " + car.toString()); 
        //}
        
        //cardao.updateCar(car2);
        //System.out.println("Print UPDATE: " + cardao.getCar(2).toString());
        //cardao.deleteCar(3);
        //System.out.println("Only BMW" + cardao.getBrandCars("BMW"));
        //System.out.println("Only BMW X5" + cardao.getBrandModel("BMW","X5"));
   
    
   
    }
            
    
    
    
}
