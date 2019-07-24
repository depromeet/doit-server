package com.server.doit.domain.dto;

import com.server.doit.domain.entity.Member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoalResultDto {
	public Member member;
	public Integer money;
	public boolean success;
}