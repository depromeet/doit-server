package com.server.doit.domain.repository;

import java.util.Optional;

import com.server.doit.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findOneByMid(Long mid);
	public Member findOneByName(String name);
	public Optional<Member> findOneByKakaoId(String id);
}