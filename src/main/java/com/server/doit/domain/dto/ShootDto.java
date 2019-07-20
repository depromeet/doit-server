package com.server.doit.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShootDto {
    private Long gid;
    private Long time;
    private Long baseTime;
    private String text;
    private Long sid;
	private Long mid;        //슛을 만든 사람 mid
	private String shootName;
	private Integer likeCount;
}