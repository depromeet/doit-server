package com.server.doit.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    public List<Participant> findAllByMember(Member member);
    public List<Participant> findAllByGoal(Goal goal);
    public Participant findOneByMemberAndGoal(Member member, Goal goal);
    public int countAllByGoalAndMember(Goal goal, Member member);
    public int countAllByGoal(Goal goal);
    public List<Participant> findAllByGoal(Goal goal);
}
