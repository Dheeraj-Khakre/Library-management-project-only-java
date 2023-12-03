package com.library;

import java.sql.SQLException;
import java.util.Scanner;

public class Library_Page {
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		System.out.println("*********** WELCOME TO DKMIDNIGHT LIBRARY ****************");
		System.out.println("  How can i hepl you...?  ");
		System.out.println(" ... please enter how you are ....???");
		System.out.println("*** Admin *** \n .. ENTER  1 \n..  user enter 2");
		try {
		int res = sc.nextInt();
		if(res==1) {
			Admin admin = new Admin();
			
		}else if(res== 2) {
			User user = new User();
		}else {
			main(null);
		}
		
		}
		catch (Exception e) {

                System.out.println("some thing is wronge ...");
            //    Library_Page.main(null);

                  
              
		}


	
	}

}
