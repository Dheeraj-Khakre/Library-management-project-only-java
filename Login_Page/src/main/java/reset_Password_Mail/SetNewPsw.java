package reset_Password_Mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/submit_new_password")
public class SetNewPsw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String psw = req.getParameter("password");
		String com_psw = req.getParameter("confirm_password");
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("resetEmail");
		System.out.println(email);
		String otp = (String) session.getAttribute("otp");
		
		System.out.println(email);
		System.out.println(psw);
		System.out.println(com_psw);
		
	
		if (psw.equals(com_psw)) {

			// out.print("password set successfully");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root",
						"zxcvbnm#321");

				PreparedStatement ps = con.prepareStatement("update users set password =? where email =?");
				ps.setString(1, com_psw);
				ps.setString(2, email);

				int x = ps.executeUpdate();
				
				System.out.println(email);
				out.println(x);
				System.out.println(x) ;
				if (x > 0) {

					out.print("<h2> password re-set  login again ... ");
					RequestDispatcher rd = req.getRequestDispatcher("login.html");
					rd.include(req, resp);

				} else {
					System.out.println("hello else ");
					out.print("<h2> something  went wronge please try again ....1</h2>");
					RequestDispatcher rd = req.getRequestDispatcher("setPassword.html");
					rd.include(req, resp);
				}

			}

			catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<h2> something  went wronge please try again ....2</h2>");
				RequestDispatcher rd = req.getRequestDispatcher("setPassword.html");
				rd.include(req, resp);
			}

		} else {
			out.print("<h2> password and comform password not match reenter ..2</h2>");
			RequestDispatcher rd = req.getRequestDispatcher("setPassword.html");
			rd.include(req, resp);

		}
	}

}
