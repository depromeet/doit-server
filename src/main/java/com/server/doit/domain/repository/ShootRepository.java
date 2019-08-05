package com.server.doit.domain.repository;

import com.server.doit.domain.entity.Member;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;

public interface ShootRepository extends JpaRepository<Shoot, Long> {
	public Shoot findOneBySid(Long sid);
	public List<Shoot> findAllByGoal(Goal goal);
	public Page<Shoot> findByGoal(Goal goal,Pageable pageable);
	public int countAllByGoalAndMakerAndDateAndIsExceeded(Goal goal,Member memeber, LocalDate localDate, boolean isExceeded);
	public int countAllByGoalAndMakerAndIsExceeded(Goal goal, Member member, boolean isExceeded);
}