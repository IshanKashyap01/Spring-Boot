package com.CN.PharmaLink.communicator;

import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.CN.PharmaLink.dto.MedicalStoreDto;

@Service
public class StoreFinderCommunicator
{
    private final RestTemplate template;
    private static final String URL = "http://localhost:8081/store";

    public StoreFinderCommunicator(RestTemplateBuilder builder)
    {
        this.template = builder.build();
    }

    public List<MedicalStoreDto> getNearestMedicalStores(Long userId, Long distance, String jwtToken)
    {
        String url = URL + "/getNearestStores/" + userId + "/" + distance;
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", jwtToken);
        HttpEntity<Void> request = new HttpEntity<>(header);
        return template.exchange(url, HttpMethod.GET, request, 
            new ParameterizedTypeReference<List<MedicalStoreDto>>() {}
        ).getBody();
    }

    public List<MedicalStoreDto> getMedicalStoresWithMedicine(String medicine, String jwtToken)
    {
        String url = URL + "/getStoresWithMedicine/" + medicine;
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", jwtToken);
        HttpEntity<Void> request = new HttpEntity<>(header);
        return template.exchange(url, HttpMethod.GET, request, 
            new ParameterizedTypeReference<List<MedicalStoreDto>>() {}
        ).getBody();
    }
}