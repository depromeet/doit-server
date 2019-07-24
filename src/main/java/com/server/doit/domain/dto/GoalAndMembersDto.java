package com.server.doit.domain.dto;

import com.server.doit.domain.entity.Goal;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class GoalAndMembersDto {
    private Goal goal;
    private List<MemberDto> memberDtoList;
}
