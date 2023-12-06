package com;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/bookdetails")
public class BookDetailsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body { background-color: #282828; color: white; font-family: Arial, sans-serif; }");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; }");
        out.println("th { background-color: #4CAF50; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Book Details</h1>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Book Name</th><th>Author Name</th><th>Title</th><th>Price</th><th>Number of Pages</th><th>Genre</th></tr>");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            while(rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("bname") + "</td><td>" + rs.getString("author_name") + "</td><td>" + rs.getString("title") + "</td><td>" + rs.getFloat("bprice") + "</td><td>" + rs.getInt("no_of_page") + "</td><td>" + rs.getString("genre") + "</td></tr>");
            }

            con.close();
        } catch(Exception e) {
            out.println("Database connection problem: " + e);
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
