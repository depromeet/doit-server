package com.server.doit.controller;

import com.server.doit.domain.dto.GoalDto;
import com.server.doit.domain.dto.GoalResultDto;
import com.server.doit.domain.dto.ParticipantDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Participant;
import com.server.doit.domain.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/api/goals/create")
    public ResponseEntity<Goal> createGoal(@RequestBody GoalDto goalDto) {
        if (!goalDto.isValid()) return ResponseEntity.badRequest().body(null);

        Goal goal = goalService.createGoal(goalDto);
        if (goal == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(goal);
    }

    @GetMapping("/api/goals/{mid}")
    public ResponseEntity<List<Goal>> getGoal(@PathVariable Long mid){
        List<Goal> goalList = goalService.getGoalList(mid);
        if(goalList == null || goalList.size() == 0) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(goalList);
    }

    @GetMapping("/api/goal/invite/from/{mid}/goal/{gid}")
    public ResponseEntity<Long> inviteGoal(@PathVariable Long mid, @PathVariable Long gid){
    	Long inviteCode = goalService.invite(mid, gid);
    	if(inviteCode == null) return ResponseEntity.badRequest().body(null);
    	
    	return ResponseEntity.ok().body(inviteCode);
    }
    
    @PostMapping("/api/goal/participate/")
    public ResponseEntity<Participant> participateGoal(@RequestBody ParticipantDto participantDto){
    	
		Participant participant = goalService.participateGoal(participantDto.getInviteId(),participantDto.getGid(), participantDto.getMid());	
		
		if (participant == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(participant);
    }
    @GetMapping("/api/goal/isend/result/{gid}")
    public ResponseEntity<List<GoalResultDto>> resultGoal(@PathVariable Long gid) {
    	List<GoalResultDto> goalResultList = goalService.finishGoalResult(gid);
    	
    	if (goalResultList == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(goalResultList);
    }
    
}
