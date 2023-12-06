package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/login_info")
public class Login_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public Login_Page() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email =request.getParameter("uname");
		String psw = request.getParameter("psw");


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			PreparedStatement ps = con.prepareStatement("select email, password from users where email =? and password =?");
			ps.setString(1, email);
			ps.setString(2, psw);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				out.print("user is exist ...");
				out.print(" <h1> WELCOME TO DK MID NIGHT WORLD ... ");
			}
			else {
				out.print("user not exist ");
				 out.print("Sorry UserName or Password Error!");  
			        RequestDispatcher rd=request.getRequestDispatcher("/login.html");  
			        rd.include(request, response);  
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
