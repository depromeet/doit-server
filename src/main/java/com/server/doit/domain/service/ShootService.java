package com.server.doit.domain.service;

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
		
		Goal goal = goalRepository.findOneByGid(shootDto.getGid());
		Shoot shoot = Shoot.builder()
				.shootName(shootDto.getShootName())
				.goal(goal)
				.build(); 
		shootRepository.save(shoot);
		return shoot;
	}	
}