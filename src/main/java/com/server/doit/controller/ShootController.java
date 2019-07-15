package com.server.doit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.service.ShootService;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class ShootController {
	private final ShootService shootService;

	@Autowired
	public ShootController(ShootService shootService) {
		this.shootService = shootService;
	}

	@PostMapping("api/shoots/create")
	public ResponseEntity<Shoot> createShoot(@RequestBody ShootDto shootDto) {
		Shoot shoot = shootService.createShoot(shootDto);
		if(shoot == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(shoot);
	}

	@PostMapping("api/shoots/{sid}/image/create")
	public ResponseEntity<String> uploadConfirmImage(@PathVariable String sid, @RequestParam("image") MultipartFile image) {
		shootService.uploadImage(sid, image);
		if(image == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().build();
	}

}