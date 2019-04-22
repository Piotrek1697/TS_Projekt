/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.DbUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Piotr Janus
 */
@WebServlet(urlPatterns = {"/FirstServlet"})
public class FirstServlet extends HttpServlet {    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        try {
            request.setAttribute("bookList", getBooks());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            response.sendError(500);
        } 
        request.getRequestDispatcher("index.jsp").forward(request, response);
        */
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.sendRedirect("adverts.jsp");
    }
 /*
    private List<Book> getBooks() throws ClassNotFoundException, SQLException {

        List<Book> bookList = null;
        final String sqlQuery = "SELECT * FROM books";

        try (Connection connection = DbUtil.getIstance().getConnection();
                Statement stat = connection.createStatement();
                ResultSet result = stat.executeQuery(sqlQuery);) {
            bookList = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String author = result.getString("author");
                String isbn = result.getString("isbn");
                String description = result.getString("description");
                boolean status = result.getBoolean("status");

                Book book = new Book(id,title,author,isbn,description,status);
                bookList.add(book);
                System.out.println(book.toString());
            }
        }
        return bookList;
    }
*/
}
