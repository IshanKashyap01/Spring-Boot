package com.codingNinjas.Bank.Account.Registration;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
You need to complete this application as mentioned in the problem 
statement build your own logic and perform the following tasks.
*/
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BankAccountRegistrationApplication 
{
	public static void main(String[] args)
	{
		// 1. Fetch context from ApplicationContext.xml and initiate scanner.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		run(context, scanner);
		context.close();
		scanner.close();
	}

	private static void run(ClassPathXmlApplicationContext context, Scanner scanner)
	{
		System.out.println("Welcome to Account Registration Application!");
		// 2. Get user details from console.
		System.out.println("Please enter your name?");
		String name = scanner.nextLine();
		User user = (User) context.getBean("myUser");
		user.setUserDetails(name);
		// 3. Get account details from user and add them to the account list.
		addAccounts(context, scanner, user);
		// 4. Display the list of accounts with their reference ids.
		System.out.println("Hi, " + user.getName() + " here is a list of your accounts:");
		for(Account account : user.getAllAccounts())
		{
			int len = account.toString().length();
			System.out.println(account.getAccountType() + " : opening balance - " + account.getBalance() +
			" Reference Id " + account.toString().substring(len - 9, len));
		}
	}

	private static void addAccounts(ClassPathXmlApplicationContext context, Scanner scanner, User user)
	{
		while(true)
		{
			System.out.println("Do you want to add" + (user.getAllAccounts().isEmpty() ? " account" : " more accounts"));
			System.out.println("1. Yes");
			System.out.println("2. No");
			if(scanner.nextInt() != 1)
			{
				break;
			}
			System.out.println("Please select the account type");
			System.out.println("1. Current");
			System.out.println("2. Savings");
			String type;
			switch(scanner.nextInt())
			{
				case 1 -> type = "currentAccount";
				case 2 -> type = "savingsAccount";
				default -> type = null;
			}
			if(type == null)
			{
				return;
			}
			Account account = (Account) context.getBean(type);
			System.out.println("Enter the opening balance");
			account.addBalance(scanner.nextDouble());
			user.addAccount(account);
		}
	}
}
/*
	for(Attendee attendee : event.getAllAttendees())
	{
		int userReferenceLength = attendee.toString().length(); //46
		System.out.println(attendee.getAttendeeName() + "\t Reference id: " + 
		attendee.toString().substring(userReferenceLength - 9, userReferenceLength));
	}
*/