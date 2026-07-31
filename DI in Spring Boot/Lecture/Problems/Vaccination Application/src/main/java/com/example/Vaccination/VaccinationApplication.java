package com.example.Vaccination;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@SpringBootApplication
public class VaccinationApplication 
{
	/*
	You need to complete this application as mentioned in the problem 
	statement build your own logic and perform the following tasks.

	 Tasks:
	1. Fetch context from ApplicationContext.xml and initiate Scanner.
	2. Fetch vaccine and User type choice.
	3. Get the required bean from context.
	4. Get the appointment details form user
	5. Display the appointment details
	6. Run the loop again to book for another user or else exit.
	 */
    public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Vaccine Application");
		run(context, scanner);
		context.close();
		scanner.close();
    }

	private static void run(ClassPathXmlApplicationContext context, Scanner scanner)
	{
		do
		{
			String vaccineChoice = inputVaccineChoice(scanner);
			String userChoice = inputUserType(scanner);
			User user = (User) context.getBean(userChoice.toLowerCase() + vaccineChoice);
			scanner.nextLine();
			if(!user.IsVaccinated())
			{
				inputUserDetails(scanner, userChoice, user);
				scanner.nextLine();
				user.setAppointment();
			}
			else
			{
				System.out.println("User is already Vaccinated");
			}
		}while(shouldRegisterSomeoneElse(scanner));
	}

	private static String inputVaccineChoice(Scanner scanner)
	{
		System.out.println("Please choose your vaccine preference:");
		System.out.println("1. Covid");
		System.out.println("2. Polio");
		System.out.println("3. Typhoid");
		switch(scanner.nextInt())
		{
			case 1: return "Covid";
			case 2: return "Polio";
			case 3: return "Typhoid";
			default: System.exit(0);
		}
		return null;
	}

	private static String inputUserType(Scanner scanner)
	{
		System.out.println("Whom do you want to vaccinate");
		System.out.println("1. Father");
		System.out.println("2. Mother");
		System.out.println("3. Self");
		System.out.println("4. Spouse");
		switch(scanner.nextInt())
		{
			case 1: return "Father";
			case 2: return "Mother";
			case 3: return "Self";
			case 4: return "Spouse";
			default: System.exit(0);
		}
		return null;
	}

	private static void inputUserDetails(Scanner scanner, String userChoice, User user)
	{
		System.out.println("Please enter " + userChoice + " details:");
		System.out.println("Name:");
		String name = scanner.nextLine();
		System.out.println("Age:");
		int age = scanner.nextInt();
		user.setUserDetails(name, age, inputTimeAndLocation(scanner));
	}

	private static TimeAndLocation inputTimeAndLocation(Scanner scanner)
	{
		System.out.print("Appointment date (YYYY-MM-DD): ");
		String date = scanner.next();
		System.out.println("Appointment time (HH:MM AM/PM): ");
		String time = scanner.next();
		System.out.println("Appointment location: ");
		String location = scanner.next();
		TimeAndLocation timeAndLocation = new TimeAndLocation();
		timeAndLocation.setDetails(time, location, date);
		return timeAndLocation;
	}

	private static boolean shouldRegisterSomeoneElse(Scanner scanner)
	{
		System.out.println("Do you want to register for someone Else");
		System.out.println("1. Yes");
		System.out.println("2. No");
		return scanner.nextInt() == 1;
	}
}