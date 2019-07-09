package com.server.doit.domain.dto;

import javax.persistence.Entity;

import com.server.doit.domain.entity.Shoot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShootAndLikeDto {
	private Shoot shoot;
	private boolean likeBoolean;  //좋아요 여부
	private boolean unLikeBoolean; //싫어요 여부
}