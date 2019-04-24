/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "advertsUpload", urlPatterns = {"/advertsUpload"})
public class AdvertsUpload extends HttpServlet {

    CarDAO cardao = new CarDAOImpl();
    UserDAO userdao = new UserDAOImpl();
    private AdvertismentDAO addao = new AdvertismentDAOImpl(cardao, userdao);
    private Advertisment advertisment;
    private Car car;
    private int price;
    private int mileage;
    private boolean demaged;
    private String vin;
    private String description;
    private int id = 0;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        advertisment = null;
        price = 0;
        mileage = 0;
        demaged = false;
        vin = null;
        description = null;
        car = null;

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("carList", cardao.getCars());
            request.getRequestDispatcher("advertsUpload.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        PrintWriter out = response.getWriter();

        if (advertisment == null) {
            id = createAdvertisment(request, user);
            response.sendRedirect("addImage.jsp");

        } else {

            try {
                addImageToAdvertisment(request, user);
                response.sendRedirect("index.jsp");
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
            }

        }
    }

    private int createAdvertisment(HttpServletRequest request, User user) throws IOException, ServletException {

        price = Integer.parseInt(request.getParameter("cena"));
        mileage = Integer.parseInt(request.getParameter("przebieg"));
        demaged = request.getParameter("uszkodzony") != null;
        vin = request.getParameter("VIN");
        description = request.getParameter("opis");

        int idCar = Integer.parseInt(request.getParameter("selectedCar"));
        car = cardao.getCar(idCar);

        BufferedImage img = null;
        InputStream in = getClass().getResourceAsStream("brak.png");
        try {
            img = LoadImage.read(in);
        } catch (IOException ex) {
            Logger.getLogger(testMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        advertisment = new Advertisment(1, user, car, mileage, demaged, vin, img, description, price);
        addao.addAdvertisment(advertisment);
        int id = addao.getAdvertisment(advertisment).getIdAdvertisment();
        return id;
    }

    private void addImageToAdvertisment(HttpServletRequest request, User user) throws IOException, ServletException {
        BufferedImage img = null;

        Part part = request.getPart("zdjecie");
        String filename = part.getSubmittedFileName();
        System.out.println("elko " + filename);
        System.out.println("ID2 " + id);
        try {
            img = ImageIO.read(new File(filename));
            img = LoadImage.scaleImage(img);
        } catch (IOException ex) {
            Logger.getLogger(testMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        advertisment = new Advertisment(id, user, car, mileage, demaged, vin, img, description, price);
        addao.updateAdvertisment(advertisment);

    }
}
