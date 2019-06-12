package com.server.doit.adapter;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.server.doit.domain.dto.KakaoUserResponse;
import com.server.doit.exception.ApiFailedException;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class KakaoAdapter {
	// 카카오 서버에 토큰을 통해 사용자 정보를 가져오기 위해 RestTemplate 사용
	private final RestTemplate restTemplate;
    //// 토큰을 이용해 사용자 정보 가져오기
    public KakaoUserResponse getUserInfo(String accessToken) {
        final URI requestUrl = UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me")
                .build(true)
                .toUri();

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        final HttpEntity httpEntity = new HttpEntity(httpHeaders);

        final ResponseEntity<KakaoUserResponse> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, httpEntity, KakaoUserResponse.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
        	// 토큰을 이용해 카카오로부터 정보 가져오는 것에 실패하면 503 error를 내준다.
            throw new ApiFailedException("Failed to get User Info from kakao", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity.getBody();
    }

}
