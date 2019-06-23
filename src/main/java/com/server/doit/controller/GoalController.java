package com.server.doit.controller;

import com.server.doit.controller.dto.GoalDto;
import com.server.doit.domain.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseWrapper<Object> createGoal(@RequestBody GoalDto goalDto) {
        if (!goalDto.isValid()) return ResponseWrapper.builder().message("FAIL").build();

        goalService.createGoal(goalDto);

        return ResponseWrapper.builder().message("SUCCESS").build();
    }
}
