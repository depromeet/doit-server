package com.server.doit.domain.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.ShootRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShootService {
	@Autowired
	private ShootRepository shootRepository;
	@Autowired
	private GoalRepository goalRepository;
	
	public Shoot createShoot(ShootDto shootDto) {
		
		Goal goal = shootDto.getGoal();
		Shoot shoot = Shoot.builder()
				.shootName(shootDto.getShootName())
				.goal(goal)
				.date(LocalDate.now())
				.build(); 
		shootRepository.save(shoot);
		return shoot;
	}
	public void deleteShoot(ShootDto shootDto) {
		Shoot shoot = shootRepository.findOneBySid(shootDto.getSid());
		shootRepository.delete(shoot);
	}
}