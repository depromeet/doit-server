package com.server.doit.domain.service;

import com.server.doit.controller.dto.GoalDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void createGoal(GoalDto goalDto) {
        goalRepository.save(getGoal(goalDto));
    }

    private Goal getGoal(GoalDto goalDto) {
        return null;
    }
}
