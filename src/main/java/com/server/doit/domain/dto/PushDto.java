package com.server.doit.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushDto {
	String token;
	String message;
}
