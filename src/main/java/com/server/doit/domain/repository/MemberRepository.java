package com.server.doit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}