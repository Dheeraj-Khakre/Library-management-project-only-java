package com.library;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmailValidator {
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

public class User {
	Scanner sc = new Scanner(System.in);

	public User() throws ClassNotFoundException, SQLException {

		System.out.println("**** WELCOME TO USER PAGE ****");
		System.out.println(".. please specify you are new user or existing user ");
		System.out.println(".. Existing user enter yes  ");
		String user = sc.next();
		if (user.equalsIgnoreCase("yes")) {
			existingUser();
		} else {
			newUser();
		}

	}

	private void newUser() {

		
			System.out.println("enter your detail here one by one : ");
			System.out.println("Enter First  Name :");
			String Fname = sc.next();
			System.out.println("Enter last  Name :");
			String Lname = sc.next();
			       
			System.out.println("Enter  Email :");
			String email = sc.next();

			System.out.println("Enter  Number :");
			String number = sc.next();
			System.out.println("Enter  Birth Date :");
			System.out.println(" in this format:  dd-MM-yyyy");
			String dob = sc.next();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(dob, dtf);
			Date DOB = Date.valueOf(date);
			System.out.println("Enter  Password :");
			String password = sc.next();

			System.out.println("Re-Enter Password  :");
			String password2 = sc.next();
			System.out.println("Enter  Country :");
			String country = sc.next();
			System.out.println("Enter  City :");
			String city = sc.next();
			// chacking all detail are right or not ok ...to be continue...
			if (number.length() != 10) {
				System.out.println("number is not valid please re-enter");
				number = sc.next();
			}
			if (!(password.equals(password2))) {
				System.out.println("password not match of conform password");
				System.out.println("Enter  Password :");
				password = sc.next();

				System.out.println("Re-Enter Password  :");
				password2 = sc.next();

			}
			String sql = "insert into users values(?,?,?,?,?,?,?,?,current_timestamp)";
			


			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "zxcvbnm#321");


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
					System.out.println("user has created ");
                    System.out.println("please login .....");
                    existingUser();
                     
				} else {
					System.out.println("user not create please Re - Enter  all information");
					newUser();
				}
			} catch (SQLException  | ClassNotFoundException s) {
				// TODO Auto-generated catch block
				s.printStackTrace();
				
			}

		


	}

	private void existingUser() throws ClassNotFoundException, SQLException {

		System.out.println(" ** please Enter your email ..? ");
		String email = sc.next();
		if (EmailValidator.isValidEmail(email)) {
			System.out.println(" ** please enter your password ..?");
			String password = sc.next();

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			PreparedStatement ps = con.prepareStatement("select email,password from users");
			ResultSet result = ps.executeQuery();
			HashMap<String, String> hm = new HashMap<String, String>();
			while (result.next()) {
				hm.put(result.getString("email"), result.getString("password"));

			}
			if (hm.containsKey(email)) {

				if (hm.get(email).equals(password)) {
					System.out.println("***** Welcome to dk midnight Library ******");
                    Books books = new Books();
                    books.userbooks();
				}

			} else {
				System.out.println(" something is wrong email invalid  ");
				System.out.println("go to home page enter  :1");
				System.out.println("go to new user enter   :2");
				System.out.println("go to existing user enter :3");
				int res = sc.nextInt();
				if(res==1)Library_Page.main(null);
				else if(res==2)newUser();
				else existingUser();
			}

		}
	}
}
