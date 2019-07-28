package com.server.doit.domain.dto;

import com.server.doit.domain.entity.Goal;

import lombok.Builder;
import lombok.Data;;

@Data
@Builder
public class GoalAndMaker {
	Goal goal;
	@Builder.Default
	boolean isHost=false;
}
