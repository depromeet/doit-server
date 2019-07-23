package com.server.doit.domain.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.server.doit.adapter.HeaderRequestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:/com/server/doit/config/config.properties")
public class PushService {
	
	@Value("${fcm.key}")
	private String SERVER_KEY;
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