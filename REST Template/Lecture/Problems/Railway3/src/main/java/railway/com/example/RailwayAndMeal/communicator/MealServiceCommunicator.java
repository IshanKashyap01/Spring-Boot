package railway.com.example.RailwayAndMeal.communicator;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
	
	public Meal getMealByPnr(long pnr) {
		String url = baseURL + "/meal" + "/" + pnr;
		return restTemplate.getForObject(url, Meal.class);
	}
	
	public void setMeal(Meal meal) {
		String url = baseURL + "/meal";
		restTemplate.postForEntity(url, meal, Object.class);
	}
	/**
	 Complete the "deleteMeal()" method by using the "exchange()" 
	 method of the "RestTemplate" to make a delete call to the given URL. 
	 **/
	
	public void deleteMeal(Long pnr)
	{
		restTemplate.exchange(baseURL + "/meal/" + pnr, HttpMethod.DELETE, null, Void.class);
	}
	
	/** 
	Complete the "updateMeal()" method by using the "exchange()" method of the 
	"RestTemplate" to make a put call to the given URL.
	**/
	public void updateMeal(Meal meal)
	{
		HttpEntity<Meal> request = new HttpEntity<>(meal);
		restTemplate.exchange(baseURL + "/meal/" + meal.getPnr(), HttpMethod.PUT, request, Void.class);
	}
	
}
