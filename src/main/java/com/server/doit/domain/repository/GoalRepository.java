package com.server.doit.domain.repository;

import com.server.doit.domain.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	public Goal findOneByGid(Long gid);
}