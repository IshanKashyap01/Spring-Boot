package com.example.Cinemaxify;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;
// 1. Beginner Code Template is already provided to you.
// 2. Just follow the below task to complete the execution of main application.
@SpringBootApplication
public class CinemaxifyApplication
{
	public static void main(String[] args) 
	{

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Cinemaxify Application");
		run(context, scanner);
	}

	private static void run(ClassPathXmlApplicationContext context, Scanner scanner)
	{
		while(true)
		{
			String userType = inputUserType(scanner);
			if(userType == null)
			{
				return;
			}
			String planType = inputPlanType(scanner);
			if(planType == null)
			{
				return;
			}
			scanner.nextLine();
			// Pick the user bean using context.getBean().
			User user = (User) context.getBean(userType + planType);
			inputUserDetails(scanner, user);
			// finally print the details by using appropriate method.
			user.getUserDetails();
			System.out.println("Do you want to purchase plan for someone else");
			System.out.println("1. Yes");
			System.out.println("2. No");
			if(scanner.nextInt() != 1)
			{
				break;
			}
		}
	}

	private static String inputUserType(Scanner scanner)
	{
		System.out.println("Please select the member you want the plan for:");
		System.out.println("1. Self\n2. Spouse");
		String type = null;
		switch (scanner.nextInt())
		{
			case 1: type = "self"; break;
			case 2: type = "spouse"; break;
			case 3: System.out.println("Exiting..."); break;
			default: System.out.println("Invalid choice.");
		}
		return type;
	}

	private static String inputPlanType(Scanner scanner)
	{
		System.out.println("Please select your plan:");
		System.out.println("1. Normal");
		System.out.println("2. Premium");
		String type = null;
		switch(scanner.nextInt())
		{
			case 1: type = "Normal"; break;
			case 2: type = "Premium"; break;
			default: System.out.println("invalid choice");
		}
		return type;
	}

	private static void inputUserDetails(Scanner scanner, User user)
	{
		// Take input for User details i.e. name , age, address etc.
		System.out.println("Please enter your name");
		String name = scanner.nextLine();
		System.out.println("Please enter your age");
		int age = scanner.nextInt();
		System.out.println("Please enter your contact");
		long contact = scanner.nextLong();
		scanner.nextLine();
		System.out.println("Please enter your address");
		String address = scanner.nextLine();
		// Set the above fetched details into the user by using appropriate method.
		user.setUserDetails(name, age, contact, address);
	}
}