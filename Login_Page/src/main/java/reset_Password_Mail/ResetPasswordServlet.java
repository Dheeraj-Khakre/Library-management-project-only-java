package reset_Password_Mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 2: Retrieve user's email
        String userEmail = request.getParameter("email");
        PrintWriter out = response.getWriter();

        // Step 3: Generate OTP
        String otp = generateRandomOTP();

        // Step 4: Store OTP (In a real application, you would store it securely, e.g., in a database)
        // For simplicity, storing it as a session attribute here
        HttpSession session = request.getSession();
        session.setAttribute("resetEmail", userEmail);
        session.setAttribute("otp", otp);

        // Step 5: Send Email
         if(  sendResetEmail(userEmail, otp)==1) {
            
        out.print("<h2> OTP sended successfully ... </h2>");
        RequestDispatcher rd = request.getRequestDispatcher("");
        // Additional logic, e.g., redirect to a confirmation page
        response.sendRedirect("getOtp.html");
         }else {
        	 out.print("<h2> please  chack your email opt not sended yet ... </h2>");
        	 RequestDispatcher rd = request.getRequestDispatcher("setOTP.html");
        	 rd.include(request, response);
         }
          
    }
    private String generateRandomOTP() {
        // Implement your OTP generation logic here
        // Example: Using a random number between 100000 and 999999
        Random random = new Random();
        return String.format("%06d", random.nextInt(900000) + 100000);
    }

    private int  sendResetEmail(String userEmail, String otp) {
        // Implement email sending logic using your chosen email service provider's library
        // Example using JavaMail API
        // Include the OTP in the email content
        // Send email to the user's email address
    	  // Set up the SMTP server.
        String host = "smtp.gmail.com";
        String from = "deerajkhakre@gmail.com";
        String pass = "knou yezb xzok dezs";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(props);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

            // Set Subject: header field
            message.setSubject("this is OTP  for reset the password Povided by dkmid night sky ");

            // Set the actual message
            message.setText("your Otp "+otp);
           
            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
            return 1;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            
           return 0;
        }
    }
}

 