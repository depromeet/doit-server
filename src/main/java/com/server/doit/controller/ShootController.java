package com.server.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.service.ShootService;

@RestController
public class ShootController {
	@Autowired
	ShootService shootService;
	
	@PostMapping("api/shoot/create")
	public ResponseEntity<Shoot> createShoot(@RequestBody ShootDto shootDto) {
		Shoot shoot = shootService.createShoot(shootDto);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);
	}
}
