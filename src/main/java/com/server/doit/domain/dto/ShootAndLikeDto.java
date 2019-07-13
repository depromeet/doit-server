package com.server.doit.domain.dto;

import com.server.doit.domain.entity.Shoot;

import lombok.Builder;
import lombok.Data;

////슛과 좋아요 싫어요에 대한 정보를 같이 보내기 위한 DTO
@Data
@Builder
public class ShootAndLikeDto {
	private Shoot shoot;
	@Builder.Default
	private boolean likeBoolean = false;  //좋아요 여부
	@Builder.Default
	private boolean unLikeBoolean = false; //싫어요 여부
}