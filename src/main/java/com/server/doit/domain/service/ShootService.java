package com.server.doit.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.ShootPageRepository;
import com.server.doit.domain.repository.ShootRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShootService {
	@Autowired
	private ShootRepository shootRepository;
	@Autowired
	private ShootPageRepository shootPageRepository;
	@Autowired
	private GoalRepository goalRepository;
	
	public Shoot createShoot(ShootDto shootDto) {
		Long gid = shootDto.getGid();
		Goal goal = goalRepository.findOneByGid(gid);
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
	
	//골 타임라인 paging read 
	public List<Shoot> readShootByGoal(Goal goal, Pageable pageable) {
//		Pageable pageable = PageRequest.of(start, end); //현재페이지, 조회할 페이지수, 정렬정보
		Page<Shoot> result = shootRepository.findByGoal(goal, pageable);
		System.out.println("페이징결과 " + result);
		List<Shoot> shootList = result.getContent();
		return shootList;
	}
}