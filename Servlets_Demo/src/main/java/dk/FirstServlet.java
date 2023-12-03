package dk;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.tomcat.jakartaee.PassThroughConverter;
@WebServlet("/dk") 
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FirstServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("servlet running sucessfully .... ");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<h1> this is my first servlet </h1>");
		out.println("<h2> welcome to dk mid night world ..  </h2>");
		String Fname = req.getParameter("Fname");
		String Lname = req.getParameter("Lname");
		String email = req.getParameter("email");
		//String dob=req.getParameter("DOB");
		String country = req.getParameter("country");
		String password = req.getParameter("psw");
		String city = req.getParameter("city");
		String  number = req.getParameter("number");
	//	out.println("<h3> name ="+name+" email = "+email+" number "+number+" </h3>" );


//
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		LocalDate date = LocalDate.parse(dob, dtf);
//		Date DOB = Date.valueOf(date);
//		
		
		
		try {


			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			String sql = "insert into users values(?,?,?,?,current_timestamp,?,?,?,current_timestamp)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Fname);
			ps.setString(2, Lname);
			ps.setString(3, email);
			ps.setString(4, number);
		//	ps.setDate(5, DOB);
			ps.setString(5, password);
			ps.setString(6, country);
			ps.setString(7, city);
			int x = ps.executeUpdate();
			if (x > 0) out.println("data entered sucessfully");
			else out.println("data entered un-sucessfully");
		} catch (SQLException | ClassNotFoundException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
			out.println("data entered un-sucessfully");
		
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
