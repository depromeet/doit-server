package com.server.doit.domain.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushDto {
	public List<String> tokenList;
	public String title;
	public String message;
}
