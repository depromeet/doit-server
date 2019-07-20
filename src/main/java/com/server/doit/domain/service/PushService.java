package com.server.doit.domain.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.server.doit.adapter.HeaderRequestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushService {
	private static final String SERVER_KEY = "AAAAy_5K1Tw:APA91bGmJTLWt5PSJ5_g1dfIFWMBFPsVbiuzS7Vvh8WVP-m7rUcsrC-wmUl1WV_wB7SSVjH2woXlps7XgeZrWvlzvS237c7hfKuLPSD6p7I2ZY1dbiY9Pmx1JY9TebZJex5K5DFtthAb";
	private static final String API_URL ="https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> pushNotification(HttpEntity<String> entity) {
		RestTemplate restTemplate = new RestTemplate();
		
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
		
		String fcmResponse = restTemplate.postForObject(API_URL, entity, String.class);
		
		return CompletableFuture.completedFuture(fcmResponse);
	}
	
}