package com.server.doit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.LikeEntity;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Shoot;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
	public LikeEntity findOneByMemberAndShoot(Member member,Shoot shoot);
}