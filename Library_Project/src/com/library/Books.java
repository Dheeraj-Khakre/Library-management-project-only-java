package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Books {
	 Scanner sc= new Scanner(System.in);
	public void BooksOperation() {
        System.out.println("******** WLECOME TO BOOKS PAGE *******");
         System.out.println(" : - show books : enter 1"); 
         System.out.println(" : - add books : enter 2"); 
         System.out.println(" : - seach books : enter 3"); 
         int x =sc.nextInt();
         if(x==1) {
        	 showAllBooks();
        	// System.out.println("shows all book");
         }else if(x==2) {
        	 addBooks();
         }else if(x==3) {
        	 seachingBooks(1);
         }
	}
	private void seachingBooks(int i) {
		System.out.println("**** searching books ****");
		//searching books code..........
		System.out.println(" which type do you choose to search ");
		System.out.println(" :- by name enter 1");
		System.out.println(" :- by Author Name enter 2");
		System.out.println(" :- by book Id 3");
		int x = sc.nextInt();
	
			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
				if(x==1) {
				
				PreparedStatement ps = con.prepareStatement("select * from books where bname=? ");
				System.out.println("please enter book name");
				String bname = sc.next();
				ps.setString(1, bname);
				ResultSet result = ps.executeQuery();
				bookData(result);
				
				}else if(x==2) {
			PreparedStatement ps = con.prepareStatement("select * from books where author_name=? ");
					 System.out.println("please enter book Author  name");
						String authorName = sc.next();
						ps.setString(1, authorName);
						ResultSet result = ps.executeQuery();
						bookData(result);
					
				}else if(x==3) {
					PreparedStatement ps = con.prepareStatement("select * from books where id=? ");
					 System.out.println("please enter book id  ");
						int id = sc.nextInt();
						ps.setInt(1, id);
						ResultSet result = ps.executeQuery();
						bookData(result);
				}else {
					System.out.println("you enter wrone input ");
					System.out.println("go to the user home page");
					if(i==1) {
						BooksOperation();
					}else{
						userbooks();
					}
					///
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		

		
	}

	private void bookData(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		if (result.next()) {
			System.out.println("Book ID : " + result.getInt("id"));
			System.out.println("Book name : " + result.getString("bname"));
			System.out.println("Book Title : " + result.getString("title"));
			System.out.println("Book author_name : " + result.getString("author_name"));

			System.out.println("Book johner : " + result.getString("johner"));
			System.out.println("Book price  : " + result.getFloat("bprice"));
			System.out.println("Number of Pages in book : " + result.getInt("no_of_page"));

		} else {

			System.out.println(" There is no any books is library .... ");
			System.out.println("please enter books ...");

		}

	}

	private void addBooks() {

		/*
		 * id int primary key, bname varchar(50) not null, title varchar(60) not null,
		 * author_name varchar(30) not null, johner varchar(50) not null, bprice float,
		 * no_of_page
		 */
		// write code for adding books....
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			PreparedStatement ps = con.prepareStatement("insert into books values(?,?,?,?,?,?,?,current_timestamp)");
			System.out.println("enter how many books wanna to enter into recod .?");
			int x = sc.nextInt();
			for (int i = 1; i <= x; i++) {
				System.out.println(" enter id of books : ");
				ps.setInt(1, sc.nextInt());

				System.out.println(" enter name of books : ");
				ps.setString(2, sc.next());
				System.out.println(" enter Title of books : ");
				ps.setString(3, sc.next());
				System.out.println(" enter Author name of books : ");
				ps.setString(4, sc.next());
				System.out.println(" enter Johner  of books : ");
				ps.setString(5, sc.next());
				System.out.println(" enter price of books : ");
				ps.setFloat(6, sc.nextFloat());
				System.out.println(" enter no. of pages   in the  books : ");
				ps.setInt(7, sc.nextInt());

				ps.addBatch();
			}
			int arr[] = ps.executeBatch();
			for (int a : arr) {
				System.out.println(a);
				if (a > 0) {
					System.out.println("book added successfully .....");
					BooksOperation();
				} else {
					System.out.println("-- some thing is wronge please enter right value");
				}

			}

			addBooks();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void userbooks() {
		 System.out.println("******** WLECOME TO BOOKS PAGE *******");
		 System.out.println(" : - show books : enter 1"); 
         System.out.println(" : - seach books : enter 2"); 
         int x =sc.nextInt();
         if(x==1) {
        	 showAllBooks();
         }else if(x==2) {
        	 seachingBooks(0);
        	
         }else {
        	 System.out.println("please enter any one ");
        	 userbooks();
         }
		   
	}
	public  void showAllBooks() {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localHost:3306/library", "root", "zxcvbnm#321");
			PreparedStatement ps = con.prepareStatement("select * from books");
			ResultSet result = ps.executeQuery();
			int i=1;
			
		     if(!result.next()) {
		    	 System.out.println(" There is no any books is library .... ");
		    	 System.out.println("please enter books ...");
		    	 
		     }else {
		    		while(result.next()) {
						  System.out.println("--- books number :"+i++ +"---");
						  System.out.println("Book ID : "+result.getInt("id"));
						  System.out.println("Book name : "+result.getString("bname"));
						  System.out.println("Book Title : "+result.getString("title"));
						  System.out.println("Book author_name : "+result.getString("author_name"));
						  
						  System.out.println("Book johner : "+result.getString("johner"));
						  System.out.println("Book price  : "+result.getFloat("bprice"));
						  System.out.println("Number of Pages in book : "+result.getInt("no_of_page"));
					}
					
		     }
			
			
			
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  
		
	}

}
