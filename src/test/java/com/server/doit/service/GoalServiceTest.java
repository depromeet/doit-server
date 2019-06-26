package com.server.doit.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.server.doit.controller.dto.GoalDto;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.service.GoalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoalServiceTest {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private GoalService goalService;

    @Test
    public void createGoal() {
        int originSize = goalRepository.findAll().size();

        GoalDto goalDto = GoalDto.builder()
                .name("test name")
                .category("cat name")
                .startDate(100L)
                .endDate(101L)
                .penalty(100)
                .color("#992233")
                .memberCount(3)
                .timerCheck(true)
                .progressType(1)
                .progressCount(3)
                .build();

        goalService.createGoal(goalDto);

        int size = goalRepository.findAll().size();

        assertThat(size).isEqualTo(originSize + 1);
    }
}
