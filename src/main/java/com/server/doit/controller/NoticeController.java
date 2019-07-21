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