package com.server.doit.domain.dto;

import java.time.LocalDate;

import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.entity.ShootConfirm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShootDto {
    private Long sid;
	private Long mid;        //슛을 만든 사람 mid
	private String shootName;
	//private LocalDate date;
	private Integer likeCount;
	private Long gid;
	
//	public Shoot toEntity() {
//		return Shoot.builder()
//				.sid(sid).shootName(shootName).date(date).likeCount(likeCount).goal(goal)
//				.build();
//		}
}