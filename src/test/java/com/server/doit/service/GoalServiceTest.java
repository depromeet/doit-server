package com.server.doit.service;

import com.server.doit.domain.dto.GoalDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Participant;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.MemberRepository;
import com.server.doit.domain.repository.ParticipantRepository;
import com.server.doit.domain.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoalServiceTest {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GoalService goalService;

    @Test
    public void createGoal() {
        int originGoalSize = goalRepository.findAll().size();
        Member member = memberRepository.getOne(1L);
        int originParticipantSize = participantRepository.findAllByMember(member).size();

        GoalDto goalDto = GoalDto.builder()
                .mid(1L)
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

        Goal goal = goalService.createGoal(goalDto);

        int goalSize = goalRepository.findAll().size();
        assertThat(goalSize).isEqualTo(originGoalSize + 1);

        List<Participant> participantList = participantRepository.findAllByMember(member);


        Participant participant = null;
        for (Participant p : participantList) {
            if (goal.getGid().equals(p.getGoal().getGid())) {
                participant = p;
                break;
            }
        }

        if (participant == null) log.error("Fail to participant");
        else assertThat(participant.isHost()).isTrue();

        int participantSize = participantRepository.findAllByMember(member).size();
        assertThat(participantSize).isEqualTo(originParticipantSize + 1);
    }
}
