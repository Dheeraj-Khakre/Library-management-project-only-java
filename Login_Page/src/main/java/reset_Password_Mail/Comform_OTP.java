package reset_Password_Mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/submit_otp")
public class Comform_OTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Comform_OTP() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 PrintWriter out = response.getWriter();
	     response.setContentType("text/html");
	     String otp = request.getParameter("otp");
	     HttpSession session = request.getSession();
	  //   String email = (String) session.getAttribute("resetEmail");
	     String  CON_otp =  (String) session.getAttribute("otp");
	     if(otp.equalsIgnoreCase(CON_otp)) {
	    	 RequestDispatcher rd = request.getRequestDispatcher("setPassword.html");
	    	 rd.forward(request, response);
	     }


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
