package com.CN.CarQuest.communicator;

import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.CN.CarQuest.dto.ReviewRequest;
import com.CN.CarQuest.dto.ReviewResponse;

@Service
public class ReviewServiceCommunicator
{
    private final RestTemplate template;
    private static final String BASE_URL = "http://localhost:8081/review";

    public ReviewServiceCommunicator(RestTemplateBuilder builder)
    {
        this.template = builder.build();
    }

    public void addReview(ReviewRequest reviewRequest, String jwtToken)
    {
        String url = BASE_URL + "/add";
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", jwtToken);
        HttpEntity<ReviewRequest> request = new HttpEntity<>(reviewRequest, header);
        template.exchange(url, HttpMethod.POST, request, ReviewResponse.class);
    }

    public List<ReviewResponse> getReview(String carName, String jwtToken)
    {
        String url = BASE_URL + "/" + carName;
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", jwtToken);
        HttpEntity<Void> request = new HttpEntity<>(header);
        return template.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<ReviewResponse>>() {})
        .getBody();
    }
}
