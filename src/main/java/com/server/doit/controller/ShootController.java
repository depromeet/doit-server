package com.server.doit.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.doit.domain.dto.ShootAndLikeDto;
import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.service.ShootService;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class ShootController {
    private final ShootService shootService;
    private final GoalRepository goalRepository;

    @Autowired
    public ShootController(ShootService shootService, GoalRepository goalRepository) {
        this.shootService = shootService;
        this.goalRepository = goalRepository;
    }

    @PostMapping("api/shoot/create")
    public ResponseEntity<Shoot> createShoot(@RequestBody ShootDto shootDto) {
        Shoot shoot = shootService.createShoot(shootDto);
        if (shoot == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(shoot);
    }

    @PostMapping("api/shoot/{sid}/image/create")
    public ResponseEntity<String> uploadConfirmImage(@PathVariable String sid, @RequestParam("image") MultipartFile image) {
        shootService.uploadImage(sid, image);
        if (image == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().build();
    }

    // ex) http://localhost:8080/api/shoot/get/1?page=1&size=5
    @GetMapping("api/shoot/get/mid/{mid}/gid/{gid}")
    public ResponseEntity<List<ShootAndLikeDto>> getShootByGoal(@PathVariable String mid, @PathVariable String gid, Pageable pageable) {
        Long goalId = Long.parseLong(gid);
        Long memId = Long.parseLong(mid);
        Goal goal = goalRepository.findOneByGid(goalId);
        List<ShootAndLikeDto> shootList = shootService.readShootByGoal(memId, goal, pageable);
        return ResponseEntity.ok().body(shootList);
    }

    @PutMapping("api/shoot/up/likecount/mid/{mid}/sid/{sid}/")
    public ResponseEntity<ShootAndLikeDto> upLikeCount(@PathVariable String mid, @PathVariable String sid) {
        Long shootId = Long.parseLong(sid);
        Long memId = Long.parseLong(mid);
        ShootAndLikeDto shoot = shootService.upLikeCount(shootId, memId);
        if (shoot == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(shoot);
    }

    @PutMapping("api/shoot/down/likecount/mid/{mid}/sid/{sid}")
    public ResponseEntity<ShootAndLikeDto> downLikeCount(@PathVariable String mid, @PathVariable String sid) {
        Long shootId = Long.parseLong(sid);
        Long memId = Long.parseLong(mid);
        ShootAndLikeDto shoot = shootService.downLikeCount(shootId, memId);
        if (shoot == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(shoot);
    }

    @PutMapping("api/shoot/up/unlikecount/mid/{mid}/sid/{sid}")
    public ResponseEntity<ShootAndLikeDto> upUnLikeCount(@PathVariable String mid, @PathVariable String sid) {
        Long shootId = Long.parseLong(sid);
        Long memId = Long.parseLong(mid);
        ShootAndLikeDto shoot = shootService.upUnLikeCount(shootId, memId);
        if (shoot == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(shoot);
    }

    @PutMapping("api/shoot/down/unlikecount/mid/{mid}/sid/{sid}")
    public ResponseEntity<ShootAndLikeDto> downUnLikeCount(@PathVariable String mid, @PathVariable String sid) {
        Long shootId = Long.parseLong(sid);
        Long memId = Long.parseLong(mid);
        ShootAndLikeDto shoot = shootService.downUnLikeCount(shootId, memId);
        if (shoot == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(shoot);
    }
}