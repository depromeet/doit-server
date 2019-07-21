package com.server.doit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.PushDto;
import com.server.doit.domain.service.PushService;

@RestController
public class PushController {
	@Autowired
	PushService pushService;
	
	@PostMapping("api/push/send")
	public @ResponseBody ResponseEntity<String> send(@RequestBody PushDto pushDto){
		JSONObject body = new JSONObject();
		List<String> tokenList = new ArrayList<String>();
		tokenList = pushDto.getTokenList();
		
		JSONArray jsonArray = new JSONArray();
		for (String token : tokenList) {
			jsonArray.put(token);			
		}
		body.put("registration_ids", jsonArray);
		
		JSONObject notification = new JSONObject();
		notification.put("title", pushDto.getTitle());
		notification.put("body", pushDto.getMessage());
		
		body.put("notification", notification);
		System.out.println(body.toString());
		
		HttpEntity<String> request = new HttpEntity<>(body.toString());
		
		CompletableFuture<String> pushNoti = pushService.pushNotification(request);
		CompletableFuture.allOf(pushNoti).join();
		
		try {
			String firebaseResponse = pushNoti.get();
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Push Notification ERROR!",HttpStatus.BAD_REQUEST);
	}
}
