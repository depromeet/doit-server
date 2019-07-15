package com.server.doit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.InviteInfo;

public interface InviteInfoRepository extends JpaRepository<InviteInfo, Long> {
	public InviteInfo findOneByInviteId(Long inviteId);
}