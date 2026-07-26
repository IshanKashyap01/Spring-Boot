package com.example.paymenow;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.services.PaymentService;

@SpringBootApplication
public class PaymenowApplication 
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		System.out.println("Enter the Payment amount:");
		Scanner sc = new Scanner(System.in);
		double amount = sc.nextInt();
		sc.nextLine();
		System.out.println("Select payment Method: (CreditCard, PayPal, BankTransfer):");
		String method = sc.next();
		String id = "";
		switch (method)
		{
			case "CreditCard": id = "creditCard"; break;
			case "PayPal": id = "payPal"; break;
			case "BankTransfer": id = "bankTransfer"; break;
			default: System.exit(0);
		}
		PaymentService service = (PaymentService) context.getBean(id);
		service.processPayment(amount);
		sc.close();
		context.close();
	}
}
