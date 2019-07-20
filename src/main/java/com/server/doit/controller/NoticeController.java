package com.server.doit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.server.doit.domain.dto.PushDto;
import com.server.doit.domain.entity.Notice;
import com.server.doit.domain.service.NoticeService;

@RestController
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("api/notice/input")
	public ResponseEntity<Notice> noticeInput(Notice notice) {
		RestTemplate restTemplate = new RestTemplate();
		List<PushDto> pushDtos = new ArrayList<PushDto>();
		PushDto pushDto = PushDto.builder().token("ecMnKhdhp18:APA91bEs_fNfmObjs6gLzm95AasSc2zBhNKAErRpkIORNLDexSMO_f6BeIvL3G8PG-vmUSsvu5KtUG4jYAgeMUJ2dvCSJ7WzsoiHMIJ3YjCVU21odBF77t--zZjr1qjMvQNh-ZME0PdX")
				.build();
		pushDtos.add(pushDto);
		restTemplate.postForEntity("http://localhost:8080/api/push/send", pushDtos, String.class);
		
		Notice result = noticeService.inputNotice(notice.getTitle(), notice.getContent());
		 if (result == null) return ResponseEntity.badRequest().body(null);
		 return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("api/notice/get/")
	public ResponseEntity<Notice> noticeGet(){
		Notice result = noticeService.getNotice();
		if (result == null) return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(result);
	}
}