package com.server.doit.domain.service;

import com.server.doit.controller.dto.GoalDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.ProgressCheckType;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.ProgressCheckTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Slf4j
@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final ProgressCheckTypeRepository progressCheckTypeRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, ProgressCheckTypeRepository progressCheckTypeRepository) {
        this.goalRepository = goalRepository;
        this.progressCheckTypeRepository = progressCheckTypeRepository;
    }

    public Goal createGoal(GoalDto goalDto) {
        Goal goal = getGoal(goalDto);

        if (goal == null) {
            log.error("Fail to create goal");
            return null;
        }

        else return goalRepository.save(goal);
    }

    private Goal getGoal(GoalDto goalDto) {
        ProgressCheckType progressCheckType = getProgressCheckType(goalDto.getProgressType());

        if (progressCheckType == null) {
            log.error("Fail to get progress check type");
            return null;
        }

        return Goal.builder()
                .goalName(goalDto.getName())
                .category(goalDto.getCategory())
                .startDate(parseToLocalDate(goalDto.getStartDate()))
                .endDate(parseToLocalDate(goalDto.getEndDate()))
                .penalty(goalDto.getPenalty())
                .progressCheckType(progressCheckType)
                .progressCheckCount(goalDto.getProgressCount())
                .themeColor(goalDto.getColor())
                .timerCheck(goalDto.getTimerCheck())
                .build();
    }

    private ProgressCheckType getProgressCheckType(int type) {
        return progressCheckTypeRepository.getOne((long) type);
    }

    private LocalDate parseToLocalDate(long time) {
        return LocalDate.ofEpochDay(time);
    }
}
