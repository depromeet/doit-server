package com.server.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.LoginDto;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("api/member/login")
	public Member login(@RequestBody LoginDto loginDto) {
		Member loginedMember = memberService.getOrCreateMember(loginDto.getKakaoToken(),loginDto.getFirebaseToken());
		return loginedMember;
	}
	
	@PutMapping("api/member/refresh/firebasetoken/mid/{mid}/token/{token}")
	public ResponseEntity<Member> refreshFirsebaseToken(@PathVariable String mid, @PathVariable String token){
		Member member = memberService.refreshFirebaseToken(mid, token);
		 if (member == null)
	            return ResponseEntity.badRequest().body(null);
		 return ResponseEntity.ok().body(member);
	}
	
}