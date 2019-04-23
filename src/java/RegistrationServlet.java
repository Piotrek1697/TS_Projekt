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
import project.ts.dao.classes.UserDAOImpl;
import project.ts.dao.interfaces.UserDAO;
import project.ts.objects.User;

/**
 *
 * @author Piotr Janus
 */
@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private UserDAO userDao = new UserDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("registration.jsp");
        } else {
            request.getRequestDispatcher("afterLogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            addUser(request);
            response.sendRedirect("login.jsp");
        }else{
            request.getRequestDispatcher("afterLogin.jsp").forward(request, response);
        }
    }

    private void addUser(HttpServletRequest request) {
        String name = request.getParameter("userName");
        String surname = request.getParameter("userSurname");
        String login = request.getParameter("userLogin");
        String password = request.getParameter("userPassword");
        if (login == null || password == null || name == null || surname == null) {
 
        } else {
            User user = new User(0, name, surname, login, password);
            userDao.addUser(user);
        }
    }

}
