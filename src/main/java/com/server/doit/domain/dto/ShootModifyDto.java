package com.server.doit.domain.dto;

import com.server.doit.domain.dto.ShootDto.ShootDtoBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShootModifyDto {
	Long sid;
	String content;
}
