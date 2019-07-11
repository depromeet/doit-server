package com.server.doit.domain.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.doit.domain.dto.GoalDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Participant;
import com.server.doit.domain.entity.ProgressCheckType;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.MemberRepository;
import com.server.doit.domain.repository.ParticipantRepository;
import com.server.doit.domain.repository.ProgressCheckTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final ProgressCheckTypeRepository progressCheckTypeRepository;
    private final MemberRepository memberRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, ProgressCheckTypeRepository progressCheckTypeRepository, MemberRepository memberRepository, ParticipantRepository participantRepository) {
        this.goalRepository = goalRepository;
        this.progressCheckTypeRepository = progressCheckTypeRepository;
        this.memberRepository = memberRepository;
        this.participantRepository = participantRepository;
    }

    public Goal createGoal(GoalDto goalDto) {
        Goal goal = getGoal(goalDto);

        if (goal == null) {
            log.error("Fail to create goal");
            return null;
        }

        goal = goalRepository.save(goal);
        Member member = memberRepository.getOne(goalDto.getMid());

        Participant participant = Participant.builder()
                .goal(goal)
                .member(member)
                .isHost(true)
                .build();

        if (participant == null) {
            log.error("Fail to create participant");
            return null;
        }

        participantRepository.save(participant);

        return goal;
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
                .startDate(LocalDate.ofEpochDay(goalDto.getStartDate()))
                .endDate(LocalDate.ofEpochDay(goalDto.getEndDate()))
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
}
