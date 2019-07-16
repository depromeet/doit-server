package com.server.doit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	
}