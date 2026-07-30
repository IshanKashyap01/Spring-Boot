package com.example.cover_mate;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.cover_mate.insurance.Insurance;

@SpringBootApplication
public class CoverMateApplication 
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		run(context, scanner);
		context.close();
		scanner.close();
	}

	private static void run(ClassPathXmlApplicationContext context, Scanner scanner)
	{
		System.out.println("Welcome to Insurance Application");
		System.out.println("What is your name?");
		String name = scanner.nextLine();
		System.out.println("What is your age");
		byte age = scanner.nextByte();
		Insurance insurance = inputInsurance(context, scanner);
		Customer customer = (Customer) context.getBean("customer" + insurance.getInsuranceName().split(" ")[0]);
		customer.setAge(age);
		customer.setName(name);
		System.out.println("Hi, " + age + " You have a " + insurance.getInsuranceName() + " premium of " +
		insurance.getInsurancePremium());
	}


	private static Insurance inputInsurance(ClassPathXmlApplicationContext context, Scanner scanner)
	{
		Insurance insurance = (Insurance) context.getBean(inputInsuranceType(scanner));
		System.out.println("You have chosen " + insurance.getInsuranceName());
		inputUserConditions(scanner, insurance);
		return insurance;
	}

	private static String inputInsuranceType(Scanner scanner)
	{
		System.out.println("Which insurance do you want?");
		System.out.println("Select 1 or 2 from Below Options");
		System.out.println("1 - Health Insurance");
		System.out.println("2 - Term Insurance");
		int choice = scanner.nextInt();
		String type = null;
		switch(choice)
		{
			case 1 -> type = "health";
			case 2 -> type = "term";
			default -> System.exit(0);
		}
		return type;
	}

	private static void inputUserConditions(Scanner scanner, Insurance insurance)
	{
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		String[] questions = getQuestions(insurance.getInsuranceName());
		System.out.println(questions[0]);
		b1 = scanner.next().charAt(0) == 'y';
		System.out.println(questions[1]);
		b2 = scanner.next().charAt(0) == 'y';
		System.out.println(questions[2]);
		b3 = scanner.next().charAt(0) == 'y';
		insurance.setInsuranceDetails(b1, b2, b3);
	}

	private static String[] getQuestions(String insuranceType)
	{
		String[] questions = new String[3];
		if(insuranceType.equalsIgnoreCase("health insurance"))
		{
			questions[0] = "Are you a Drinker (y/n)?";
			questions[1] = "Are you a Smoker (y/n)";
			questions[2] = "Do you have any prior health conditions (y/n)?";
		}
		else
		{
			questions[0] = "Are you married (y/n)?";
			questions[1] = "Are you salaried (y/n)?";
			questions[2] = "Do you have any children (y/n)?";
		}
		return questions;
	}
}
