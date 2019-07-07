package com.server.doit.domain.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;

public interface ShootPageRepository extends Repository<Shoot, Long> {
	public Page<Shoot> findByGoal(Goal goal,Pageable pageable);
}