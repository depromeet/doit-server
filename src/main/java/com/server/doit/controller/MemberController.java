package com.server.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.LoginDto;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("api/user/login")
	public Member login(@RequestBody LoginDto loginDto) {
		Member loginedMember = memberService.getOrCreateMember(loginDto.getKakaoToken());
		return loginedMember;
	}
}