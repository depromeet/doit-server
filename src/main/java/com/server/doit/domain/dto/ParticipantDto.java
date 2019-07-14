package com.server.doit.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantDto {
    private Long inviteId;	//초대 코드
	private Long gid;        //참가할 골의 아이디
	private Long mid;		//참가할 멤버의 아이디
}