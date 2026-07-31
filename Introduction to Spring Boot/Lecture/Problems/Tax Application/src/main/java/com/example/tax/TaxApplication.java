package com.example.tax;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TaxApplication
{
	public static void main(String[] args)
	{
		// Take ClassPathXmlApplicationContext from applicationContext.xml file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Tax Payment Application");
		while (true) 
		{
			System.out.println("Please select which tax you want to pay: \n1. Income \n2. Property\n3. Exit");
			int userChoice = scanner.nextInt();
			String taxChoice = "";
			switch (userChoice) 
			{
				// Set the taxChoice string as the Income tax bean id.
				case 1: taxChoice = "incomeTax"; break;
				// Set the taxChoice string as the Property tax bean id.
				case 2: taxChoice = "propertyTax"; break;
				// Print the message "Exiting..." and return.
				case 3: System.out.println("Exiting..."); return;
				// Print the message "Invalid choice" and return.
				default: System.out.println("Invalid choice"); return;
			}
			// Pick the tax bean using context.getBean() method using taxChoice string.
			Tax tax = (Tax) context.getBean(taxChoice);
			if(tax.isTaxPayed())
			{
				System.out.println("You have already paid " + tax.getTaxType() + " tax.");
			}
			else
			{
				System.out.println("Please enter your Income/Property value:");
				double amount = scanner.nextDouble();
				tax.setTaxableAmount(amount);
				tax.calculateTaxAmount();
				tax.payTax();
			}
		}
	}
}