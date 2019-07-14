package com.server.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.controller.dto.GoalDto;
import com.server.doit.domain.dto.ParticipantDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Participant;
import com.server.doit.domain.service.GoalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    @RequestMapping(value = "/api/goal/create")
    public ResponseEntity<Goal> createGoal(@RequestBody GoalDto goalDto) {
        if (!goalDto.isValid()) return ResponseEntity.badRequest().body(null);

        Goal goal = goalService.createGoal(goalDto);
        if (goal == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(goal);
    }
    
    @GetMapping("/api/goal/invite/from/{mid}/goal/{gid}")
    public ResponseEntity<Long> inviteGoal(@PathVariable String mid,@PathVariable String gid){
    	Long goalId = Long.parseLong(gid);
		Long memId = Long.parseLong(mid);
    	Long inviteCode = goalService.invite(memId, goalId);
    	if(inviteCode == null) return ResponseEntity.badRequest().body(null);
    	
    	return ResponseEntity.ok().body(inviteCode);
    }
    
    @PostMapping("/api/goal/participate/")
    public ResponseEntity<Participant> participateGoal(@RequestBody ParticipantDto participantDto){
    	
		Participant participant = goalService.participateGoal(participantDto.getInviteId(),participantDto.getGid(), participantDto.getMid());	
		
		if (participant == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(participant);
    }
    
}
