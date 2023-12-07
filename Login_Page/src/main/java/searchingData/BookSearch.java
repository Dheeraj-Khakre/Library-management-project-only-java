package searchingData;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/booksearchs")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;



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
         out.println("<table border=1 width=80% height=20%>");
        out.println("<tr><th>ID</th><th>Book Name</th><th>Author Name</th><th>Title</th><th>Price</th><th>Number of Pages</th><th>Genre</th></tr>");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");

            PreparedStatement stmt = con.prepareStatement("select * from books where  bname=?");
            stmt.setString(1,  request.getParameter("book_name"));
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("bname") + "</td><td>" + rs.getString("author_name") + "</td><td>" + rs.getString("title") + "</td><td>" + rs.getFloat("bprice") + "</td><td>" + rs.getInt("no_of_page") + "</td><td>" + rs.getString("genre") + "</td></tr>");
          
                RequestDispatcher rd= request.getRequestDispatcher("admin_home.html");
            	rd.include(request, response);
            }
            else {
            	   out.print("<h1> book not found ...</h1>");
                   RequestDispatcher rd= request.getRequestDispatcher("admin_home.html");
               	rd.include(request, response);

            }

            con.close();
        } catch(Exception e) {
            out.println("Database connection problem: " + e);
            out.print("<h1> book not found ...</h1>");
            RequestDispatcher rd= request.getRequestDispatcher("admin_home.html");
        	rd.include(request, response);
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
