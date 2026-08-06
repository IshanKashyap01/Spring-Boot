package railway.com.example.RailwayAndMeal.communicator;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import railway.com.example.RailwayAndMeal.Entity.Meal;

@Service
public class MealServiceCommunicator
{
	
	private final RestTemplate restTemplate;
	String baseURL = "http://localhost:8081/pantry";
	
	MealServiceCommunicator(RestTemplateBuilder restTemplateBuilder)
	{
		restTemplate = restTemplateBuilder.build();
	}
	
	public Meal getMealByPnr(long pnr) 
	{
		String url = baseURL + "/meal/" + pnr;
		return restTemplate.getForObject(url, Meal.class);
	}
	
	public void setMeal(Meal meal)
	{
		/** Use the "postForEntity()" method to make a post call.
	 	This method saves a meal object in the "Meal Application". **/
		String url = baseURL + "/meal/" + meal.getPnr();
		restTemplate.postForEntity(url, meal, Void.class);
	}
	
}
