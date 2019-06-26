package com.server.doit.domain.repository;

import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    public List<Participant> findAllByMember(Member member);
}
