package com.library;

import java.sql.*;
import java.util.Scanner;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

public class Admin {

	Scanner sc = new Scanner(System.in);

	private void name(String adname, String password2) {
		final String adminName = "dkmid";
		final String password = "DK#321";
		if (adminName.equalsIgnoreCase(adname) && password.equalsIgnoreCase(password2)) {
			System.out.println("   Access Granted ...");
			mainAdminPage();
		} else {
			System.out.println("   Access Denied ...");
			System.out.println(" some thing is wronge ...");
			System.out.println(" please re - enter ");
			new Admin();
		}
	}

	public void mainAdminPage() {
		System.out.println("---- welcome Admin DK MIDNIGHT SKY ----");
		System.out.println("enter which field  to see...?   ");
		System.out.println(" * books");
		System.out.println(" * Users");
		System.out.println("enter books or users ");
		String str =sc.next();
		if (str.equalsIgnoreCase("books")) {
			/// book methods...
			
			books();
		} else if (str.equalsIgnoreCase("users")) {
			// users methods...
			
			users();
		} else {
			System.out.println("you enter wrong input please re-enter");
			mainAdminPage();
		}
	}

	private void users() {

		System.out.println("show all users enter : - yes");
		if (sc.next().equalsIgnoreCase("yes")) {
			allusers();
		}
		System.out.println(" ---- search users ----");
		System.out.println(" -- you wanat to search user enter yes");
		if (sc.next().equalsIgnoreCase("yes")) {
			searchingUser();
		}
    mainAdminPage();
	}

	private void searchingUser() {
		System.out.println(" which type do you choose to search ");
		System.out.println(" :- by name enter 1");
		System.out.println(" :- by email enter 2");
		System.out.println(" :- by number enter 3");

		/// searching by name

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			PreparedStatement ps = con.prepareStatement("select * from users where fname=?");
                  int x=sc.nextInt();
			if (x == 1) {
				//PreparedStatement ps = con.prepareStatement("select * from users where fname=?");
				System.out.println("enter first name of user");
				ps.setString(1, sc.next());
				ResultSet result = ps.executeQuery();
				info(result);
				ps.close();

			} else if (x == 2) {
			PreparedStatement	 ps1 = con.prepareStatement("select * from users where email= ?");
				 System.out.println("enter email of user");
				     String email =sc.next();
				     if(EmailValidator.isValidEmail(email)) {
					ps1.setString(1, email);
					ResultSet result = ps1.executeQuery();
					info(result);
				     }
				     else {
				    	 System.out.println(">> please chack your email is wronge ... ");
				    	 System.out.println("go for searching enter 1 ");
				    	 if(sc.nextInt()==1)searchingUser();else mainAdminPage();
				     }
				     ps1.close();
				/// searching by email
			} else if (x == 3) {
				/// searching by number..
				PreparedStatement ps2 = con.prepareStatement("select * from users where number=?");
				 System.out.println("enter mobile number of user");
				     String no =sc.next();
				     if(no.length()==10) {
					ps2.setString(1, no);
					ResultSet result = ps2.executeQuery();
					info(result);
				     }
				     else {
				    	 System.out.println(">> please chack your number is wronge >> ");
				    	 System.out.println("go for searching enter 1 ");
				    	 if(sc.nextInt()==1)searchingUser();else mainAdminPage();
				     }
			}
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void info(ResultSet result) throws SQLException {
		  if(!result.next()) {
			  System.out.println(" there is no matching datail of user  ");
			  System.out.println("user not exist......");
		  }

		while (result.next()) {
			System.out.println("your name : " + result.getString("Fname") + " " + result.getString("Lame"));
			System.out.println("your Email : " + result.getString("email"));
			System.out.println("your number : " + result.getString("number"));
			System.out.println("your birth date : " + result.getString("DOB"));
			System.out.println("your password : " + result.getString("password"));
			System.out.println("your  country: " + result.getString("country"));
			System.out.println("your city  : " + result.getString("city"));
			System.out.println("date and time of sign in " + result.getDate("date_Creation"));
			System.out.println(" ------------------//------------------------");

		}
	}

	private void allusers() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			PreparedStatement ps = con.prepareStatement("select * from users");
			ResultSet result = ps.executeQuery();
			int i = 1;
			while (result.next()) {

				System.out.println(" --- user number :- " + i++);

				System.out.println("your name : " + result.getString("Fname") + " " + result.getString("Lame"));
				System.out.println("your Email : " + result.getString("email"));
				System.out.println("your number : " + result.getString("number"));
				System.out.println("your birth date : " + result.getString("DOB"));
				System.out.println("your password : " + result.getString("password"));
				System.out.println("your  country: " + result.getString("country"));
				System.out.println("your city  : " + result.getString("city"));

			}
			con.close();
			
			System.out.println(" ----------thisis all users----------\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void books() {
		Books  books = new Books();
		books.BooksOperation();
		
mainAdminPage();
	}

	public Admin() {
		System.out.println("**** WELCOME TO ADMIN PAGE ****");
		System.out.println("     please enter admin name ");
		String adname = sc.next();
		System.out.println("     please enter Password ");
		String password = sc.next();
		name(adname, password);

	}

}
