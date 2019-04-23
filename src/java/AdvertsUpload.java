/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import project.ts.dao.classes.AdvertismentDAOImpl;
import project.ts.dao.classes.CarDAOImpl;
import project.ts.dao.classes.UserDAOImpl;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.Advertisment;
import project.ts.objects.Car;
import project.ts.objects.User;
import util.LoadImage;

/**
 *
 * @author Rozma
 */
@WebServlet(name = "advertsUpload", urlPatterns = {"/advertsUpload"})
public class AdvertsUpload extends HttpServlet {

   
    CarDAO cardao = new CarDAOImpl();
    UserDAO userdao = new UserDAOImpl();
    private AdvertismentDAO addao = new AdvertismentDAOImpl(cardao,userdao);
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
         if (user == null) {
            response.sendRedirect("login.jsp");
         } else {
            request.getRequestDispatcher("advertsUpload.jsp").forward(request, response);
        }
           
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        HttpSession session = request.getSession(true); 
        User user = (User) session.getAttribute("user");
//        createAdvertisment(request, user);
        
        
    }
    
    private void createAdvertisment(HttpServletRequest request, User user) throws IOException, ServletException {
        LoadImage loadImage = new LoadImage();
        
        int price = Integer.parseInt(request.getParameter("cena"));
        int mileage = Integer.parseInt(request.getParameter("przebieg"));
        boolean demaged = request.getParameter( "uszkodzony" ) != null;
        String vin = request.getParameter("VIN");
        //Part part = request.getPart("zdjecie");
        String description = request.getParameter("opis");
      
        BufferedImage img = null;
                             
        try {
            img = ImageIO.read(new File("C:\\Users\\Rozma\\Documents\\GitHub\\TS_Projekt\\src\\java\\bmwx5.jpg"));
            //img = ImageIO.read(new File(part.toString()));
            img = loadImage.scaleImage(img);
        } catch (IOException ex) {
            Logger.getLogger(testMain.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        Car car1 = new Car(1, "BMW", "X5", 2015, "petrol", 2998.5, 263, 5);
        Advertisment advertisment1 = new Advertisment(1,user,car1,mileage,demaged,vin,img,description,3000);
        addao.addAdvertisment(advertisment1);
        
       }
    }


