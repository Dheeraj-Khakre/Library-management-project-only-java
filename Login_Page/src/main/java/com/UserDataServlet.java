package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/userdata")
public class UserDataServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");

            PreparedStatement stmt = con.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();

            out.println("<table border=1 width=80% height=60%>");
            out.println("<tr><th>email</th><th>First Name</th><sth>Last Name</th><th>number</th><th>Birth date</th><th>Country</th><th>City</th><th>Sign in Info</th><tr>");

            while (rs.next()) {
                String number = rs.getString("number");
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("Lame");
                String email = rs.getString("email");
                String DOb= rs.getString("DOB");
    			String country =  rs.getString("country");
    		String city =  rs.getString("city");
    		  Date date= rs.getDate("date_Creation");




                out.println("<tr><td>" + email + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" +number + "</td><td>" +DOb + "</td><td>" +country+"</td><td>" +city + "</td><td>" +date +"</td></tr>");
            }
            out.println("</table>");
            out.close();
        } catch (Exception e) {
            out.println("error");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.doPost(req, resp);
    }
}
