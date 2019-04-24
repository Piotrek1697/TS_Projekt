/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ts.dao.classes.AdvertismentDAOImpl;
import project.ts.dao.classes.CarDAOImpl;
import project.ts.dao.classes.UserDAOImpl;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.Advertisment;
import project.ts.objects.Car;
import project.ts.objects.User;

/**
 *
 * @author Piotr Janus
 */
@WebServlet(name = "modifyAdvert", urlPatterns = {"/modifyAdvert"})
public class ModifyAdvert extends HttpServlet {

    private CarDAO carDao = new CarDAOImpl();
    private UserDAO userDao = new UserDAOImpl();
    private AdvertismentDAO advertDao = new AdvertismentDAOImpl(carDao, userDao);
    private Car car;
    private double price;
    private int mileage;
    private boolean demaged;
    private String vin;
    private String description;
    private int id = 0;
    private Advertisment advertisment;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAdvert = request.getParameter("modifyBTN");
        advertisment = advertDao.getAdvertisment(Integer.parseInt(idAdvert));
        
        request.setAttribute("advertisment", advertisment);
        request.setAttribute("carList", carDao.getCars());
        
        request.getRequestDispatcher("modifyAdvert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        price = Double.parseDouble(request.getParameter("cena"));
        mileage = Integer.parseInt(request.getParameter("przebieg"));
        demaged = request.getParameter("uszkodzony") != null;
        vin = request.getParameter("VIN");
        description = request.getParameter("opis");
        BufferedImage img = advertisment.getImage();
        
        int idCar = Integer.parseInt(request.getParameter("selectedCar"));
        car = carDao.getCar(idCar);
        
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        
        Advertisment advert = new Advertisment(advertisment.getIdAdvertisment(), user, car, mileage, demaged, vin, img, description, price);
        
        System.out.println("ELO: "+advert);
        System.out.println("ELO: "+img.getHeight());
        advertDao.updateAdvertisment(advert);
        
        response.sendRedirect("index.jsp");
    }


}
