package com.example.car_dealership;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.car_dealership.car.Car;

@SpringBootApplication
public class CarDealershipApplication
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		run(context, scanner);
		context.close();
	}

	private static void run(ClassPathXmlApplicationContext context, Scanner scanner)
	{
		String name = inputName(scanner);
		String carType = inputCarType(scanner);
		if(carType.equals("family"))
		{
			System.out.println("Do you want sports tyres (y/n)?");
			if(scanner.next().charAt(0) == 'y')
			{
				carType += "SportsTyre";
			}
		}
		Car car = (Car) context.getBean(carType);
		car.setOwnerName(name);
		System.out.println(car.getInfo());
	}
	
	private static String inputName(Scanner scanner)
	{
		System.out.println("Hi, please enter your name");
		return scanner.nextLine();
	}

	private static String inputCarType(Scanner scanner)
	{
		String carType = null;
		do
		{
			System.out.println("Please select a car of your choice");
			System.out.println("1. Family Car");
			System.out.println("2. Sports Car");
			System.out.println("3. Truck");
			carType = switch(scanner.nextInt())
			{
				case 1 -> "family";
				case 2 -> "sports";
				case 3 -> "truck";
				default -> null;
			};
		}while(carType == null);
		return carType;
	}

}
