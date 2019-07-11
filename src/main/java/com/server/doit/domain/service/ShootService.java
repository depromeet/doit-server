package com.server.doit.domain.service;

import java.time.LocalDate;

import com.server.doit.domain.entity.ShootConfirm;
import com.server.doit.domain.entity.ShootConfirmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.ShootRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ShootService {
    private final ShootRepository shootRepository;
    private final GoalRepository goalRepository;

    @Autowired
    public ShootService(ShootRepository shootRepository, GoalRepository goalRepository) {
        this.shootRepository = shootRepository;
        this.goalRepository = goalRepository;
    }

    public Shoot createShoot(ShootDto shootDto) {
        Goal goal = goalRepository.getOne(shootDto.getGid());
        Shoot shoot = Shoot.builder()
                .goal(goal)
                .date(LocalDate.now())
                .build();
        shoot = shootRepository.save(shoot);

        String text = shootDto.getText();
        if (text != null) {
            ShootConfirm.builder()
                    .shoot(shoot)
                    .shootConfirmType(ShootConfirmType.TEXT)
                    .content(text)
                    .build();
        }

        Long time = shootDto.getTime();
        if (time != null) {
            ShootConfirm.builder()
                    .shoot(shoot)
                    .shootConfirmType(ShootConfirmType.TIMER)
                    .content(String.valueOf(time))
                    .build();
        }

        return shoot;
    }

    public void uploadImage(String sid, MultipartFile file) {

    }
}