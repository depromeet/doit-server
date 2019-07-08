package com.server.doit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.ShootRepository;
import com.server.doit.domain.service.ShootService;

@RestController
public class ShootController {
	@Autowired
	ShootService shootService;
	@Autowired
	GoalRepository goalRepository;
	
	@PostMapping("api/shoot/create")
	public ResponseEntity<Shoot> createShoot(@RequestBody ShootDto shootDto) {
		Shoot shoot = shootService.createShoot(shootDto);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);
	}
	// ex) http://localhost:8080/api/shoot/get/1?page=1&size=5
	@GetMapping("api/shoot/get/{gid}")
	public ResponseEntity<List<Shoot>> getShootByGoal(@PathVariable String gid,Pageable pageable) {
		Long goalId = Long.parseLong(gid);
		Goal goal = goalRepository.findOneByGid(goalId);
		List<Shoot> shootList = shootService.readShootByGoal(goal, pageable);
		return ResponseEntity.ok().body(shootList);
	}
	
	@PutMapping("api/shoot/up/likecount/{sid}")
	public ResponseEntity<Shoot> upLikeCount(@PathVariable String sid) {
		Long shootId = Long.parseLong(sid);
		Shoot shoot =shootService.upLikeCount(shootId);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);	
	}
	
	@PutMapping("api/shoot/down/likecount/{sid}")
	public ResponseEntity<Shoot> downLikeCount(@PathVariable String sid) {
		Long shootId = Long.parseLong(sid);
		Shoot shoot =shootService.downLikeCount(shootId);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);	
	}
	
	@PutMapping("api/shoot/up/unlikecount/{sid}")
	public ResponseEntity<Shoot> upUnLikeCount(@PathVariable String sid) {
		Long shootId = Long.parseLong(sid);
		Shoot shoot =shootService.upUnLikeCount(shootId);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);	
	}
	
	@PutMapping("api/shoot/down/unlikecount/{sid}")
	public ResponseEntity<Shoot> downUnLikeCount(@PathVariable String sid) {
		Long shootId = Long.parseLong(sid);
		Shoot shoot =shootService.downUnLikeCount(shootId);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);	
	}
}