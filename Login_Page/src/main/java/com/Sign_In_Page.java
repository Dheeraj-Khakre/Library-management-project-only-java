package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/sign_in")
public class Sign_In_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Sign_In_Page() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("servlet running sucessfully .... ");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
	//	out.println("<h1> this is my first servlet </h1>");
	//	out.println("<h2> welcome to dk mid night world ..  </h2>");
		String Fname = req.getParameter("Fname");
		String Lname = req.getParameter("Lname");
		String email = req.getParameter("email");
		String dob=req.getParameter("DOB");
		String country = req.getParameter("country");
		String password = req.getParameter("psw");
		String r_psw=req.getParameter("psw-repeat");
		if(!password.equals(r_psw)) {
			/// go for the same page 
			out.print("password should be same ");
			 
			 RequestDispatcher  rd=req.getRequestDispatcher("/signIn.html");  
		        rd.include(req, res);  
			
		}
		String city = req.getParameter("city");
		String  number = req.getParameter("number");
		


       //    out.print(" <h1> DOB = "+dob+"</h1>");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dob, dtf);
		Date DOB = Date.valueOf(date);
		

		try {


			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			String sql = "insert into users values(?,?,?,?,?,?,?,?,current_timestamp)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Fname);
			ps.setString(2, Lname);
			ps.setString(3, email);
			ps.setString(4, number);
			ps.setDate(5, DOB);
			ps.setString(6, password);
			ps.setString(7, country);
			ps.setString(8, city);
			int x = ps.executeUpdate();
			if (x > 0) {
				out.println("data entered sucessfully");
				String Name = Fname+Lname;
				Cookie cookieName= new Cookie("name",Name);
				Cookie cookieEmail= new Cookie("email",email);
				Cookie cookiePsw= new Cookie("passwors",password);
				res.addCookie(cookieName);
				res.addCookie(cookieEmail);
				res.addCookie(cookiePsw);
				out.println("cookies added sucessefully");
				RequestDispatcher rd= req.getRequestDispatcher("/login.html");
				rd.include(req, res);
				
			}
			else out.println("data entered un-sucessfully");
		} catch (SQLException | ClassNotFoundException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
			out.println("user is Already Exist ...");
			 
			 RequestDispatcher  rd=req.getRequestDispatcher("/login.html");  
		        rd.include(req, res);  
		
		}
		


	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
