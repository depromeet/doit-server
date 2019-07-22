package com.server.doit.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.entity.UnLikeEntity;

public interface UnLikeRepository extends JpaRepository<UnLikeEntity, Long> {
	public UnLikeEntity findOneByMemberAndShoot(Member member,Shoot shoot);
	public List<UnLikeEntity> findAllByShoot (Shoot shoot);
}