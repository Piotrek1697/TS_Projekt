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
import javax.servlet.http.HttpSession;
import project.ts.dao.classes.AdvertismentDAOImpl;
import project.ts.dao.classes.CarDAOImpl;
import project.ts.dao.classes.UserDAOImpl;
import project.ts.dao.interfaces.AdvertismentDAO;
import project.ts.dao.interfaces.CarDAO;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.User;

/**
 *
 * @author Piotr Janus
 */
@WebServlet(name = "userAdverts", urlPatterns = {"/userAdverts"})
public class UserAdverts extends HttpServlet {

    private CarDAO carDao = new CarDAOImpl();
    private UserDAO userDao = new UserDAOImpl();
    private AdvertismentDAO advertDao = new AdvertismentDAOImpl(carDao, userDao);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        }
        else{
            request.setAttribute("advertList", advertDao.getUserAdverts(user.getIdUser()));
            request.setAttribute("user", user);
            request.getRequestDispatcher("userAdverts.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAdvert = request.getParameter("deleteBTN");
        advertDao.deleteAdvertisment(Integer.parseInt(idAdvert));
        doGet(request, response);
    }


}
