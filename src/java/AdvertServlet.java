/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.ts.dao.classes.AdvertismentDAOImpl;
import project.ts.dao.classes.CarDAOImpl;
import project.ts.dao.classes.UserDAOImpl;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;

/**
 *
 * @author Piotr Janus
 */
@WebServlet(urlPatterns = {"/adverts"}, name="adverts")
public class AdvertServlet extends HttpServlet {

    private CarDAO carDao = new CarDAOImpl();
    private UserDAO userDao = new UserDAOImpl();
    private AdvertismentDAO advertDao = new AdvertismentDAOImpl(carDao, userDao);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        
        request.setAttribute("advertList", advertDao.getBrandModalAdvertisment(brand, model));
        
        request.getRequestDispatcher("adverts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
