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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDAO userDao = new UserDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.getRequestDispatcher("login.jsp").forward(request, response);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        }
        else{
            request.getRequestDispatcher("afterLogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(30 * 60);          //Ustawienie czasu sesji użytkownika na 30 minut
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = createUser(request);
            if (user != null) {
                session.setAttribute("user", user);
                request.getRequestDispatcher("afterLogin.jsp").forward(request, response);
            }else{
                //Tutaj trzeba by było wywalić jakieś okno gdzie jest napisane, że nie błędne hasło albo login
            }
        }
    }

    private User createUser(HttpServletRequest request) {
        String login = request.getParameter("userLogin");
        String password = request.getParameter("userPassword");
        if (login == null || password == null) {
            return null;
        } else {
            return userDao.getUserByLogin(login, password);
        }
    }

}
