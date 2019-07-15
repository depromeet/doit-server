package com.server.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.GoalDto;
import com.server.doit.domain.entity.Goal;
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

    @PostMapping("/api/goals/create")
    public ResponseEntity<Goal> createGoal(@RequestBody GoalDto goalDto) {
        if (!goalDto.isValid()) return ResponseEntity.badRequest().body(null);

        Goal goal = goalService.createGoal(goalDto);
        if (goal == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(goal);
    }
}
