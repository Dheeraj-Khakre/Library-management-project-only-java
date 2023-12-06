package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin_loging")
public class Admin_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public Admin_Page() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          PrintWriter out = response.getWriter();
          response.setContentType("text/html");
         String username = "dkmidnightsky";
         String psw= "dkmid#321";
         if(username.equalsIgnoreCase(request.getParameter("uname"))) {
        	 if(psw.equals(request.getParameter("psw"))) {
        		 out.print("<h2> welcome to Admin page ...");
        		 RequestDispatcher rd = request.getRequestDispatcher("/admin_home.html");
        		   rd.forward(request, response);
        			}else {
        		 out.print("password is wronge... ");
        		 RequestDispatcher  rd=request.getRequestDispatcher("/admin_Login_Page.html");  
 		        rd.include(request, response);  
        	 }
        	 
         }else {
        	 out.print("<h2 style=\"color:  rgba(255,255,255,0.47)\">user name  is wronge... </h2> ");
    		 RequestDispatcher  rd=request.getRequestDispatcher("/admin_Login_Page.html");  
		        rd.include(request, response);
        	 
         }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
