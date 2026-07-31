package com.example.CustomerServicedemo;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.Customers.CustomerCare;

@SpringBootApplication
public class CustomerServicedemoApplication
{
	/*
	You need to complete this application as mentioned in the problem 
	statement build your own logic and perform the following tasks.
	Tasks:
		1. Load the beans from ApplicationContext.xml
		2. Display all the departments available and get the input from user.
		3. Get the message from user and store it into the respective department.
	*/
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		String name = inputCustomerName(scanner);
		String dept = inputDepartment(scanner);
		// move scanner to the next line as the last input was an int and the next will be a text line
		scanner.nextLine();
		if(dept != null)
		{
			CustomerCare department = (CustomerCare) context.getBean(dept);
			serveUser(department, name, scanner);
		}
		scanner.close();
		context.close();
	}

	private static String inputCustomerName(Scanner scanner)
	{
		System.out.println("Welcome to our Customre Care application");
		System.out.println("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Thanks for reaching us " + name);
		return name;
	}

	private static String inputDepartment(Scanner scanner)
	{
		System.out.println("Please select a department to connect to:");
		System.out.println("1. Payment Department");
		System.out.println("2. Query Department");
		System.out.println("3. Sales Department");
		System.out.println("0. Exit");
		int userChoice = scanner.nextInt();
		switch (userChoice)
		{
			case 1: return "paymentDepartment";
			case 2: return "queryDepartment";
			case 3: return "salesDepartment";
			default: System.out.println("You have exited the application.");
		}
		return null;
	}

	private static void serveUser(CustomerCare department, String user, Scanner scanner)
	{
		department.setCustomerName(user);
		department.getService();
		String issue = scanner.nextLine();
		department.setProblem(issue);
		department.getProblem();
	}
}