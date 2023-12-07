package searchingData;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/usersearch")
public class UserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     
		  response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	          String email = request.getParameter("eamilsearch");

	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");

	            PreparedStatement stmt = con.prepareStatement("select * from users where  email =?");
	                     stmt.setString(1, email);
	            ResultSet rs = stmt.executeQuery();

	            out.println("<table border=1 width=80% height=20%>");
	            out.println("<tr><th>email</th><th>First Name</th><th>Last Name</th><th>number</th><th>Birth date</th><th>Country</th><th>City</th><th>Sign in Info</th><tr>");

	            if(rs.next()) {
	                String number = rs.getString("number");
	                String firstName = rs.getString("Fname");
	                String lastName = rs.getString("Lame");

	                String DOb= rs.getString("DOB");
	    			String country =  rs.getString("country");
	    		String city =  rs.getString("city");
	    		  Date date= rs.getDate("date_Creation");




	                out.println("<tr><td>" + email + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" +number + "</td><td>" +DOb + "</td><td>" +country+"</td><td>" +city + "</td><td>" +date +"</td></tr>");
	               	RequestDispatcher rd= request.getRequestDispatcher("admin_home.html");
	            	rd.include(request, response);
	            }else {
	            	out.print("<h1>  user not found please chack user  dwtails  </h1>");
	            	RequestDispatcher rd= request.getRequestDispatcher("admin_home.html");
	            	rd.include(request, response);
	            }
	            out.println("</table>");
	            out.close();
	        } catch (Exception e) {
	        	out.print("<h1> ");
	            out.println("error");
	        }
		
			}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
