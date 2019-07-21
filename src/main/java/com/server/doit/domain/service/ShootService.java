package com.server.doit.domain.service;

import com.server.doit.domain.dto.ShootAndLikeDto;
import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.*;
import com.server.doit.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShootService {
    private final ShootRepository shootRepository;
    private final GoalRepository goalRepository;
    private final StorageService storageService;
    private final ShootConfirmRepository shootConfirmRepository;
    private final LikeRepository likeRepository;
    private final UnLikeRepository unLikeRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ShootService(ShootRepository shootRepository, GoalRepository goalRepository, StorageService storageService, ShootConfirmRepository shootConfirmRepository, LikeRepository likeRepository, UnLikeRepository unLikeRepository, MemberRepository memberRepository) {
        this.shootRepository = shootRepository;
        this.goalRepository = goalRepository;
        this.storageService = storageService;
        this.shootConfirmRepository = shootConfirmRepository;
        this.likeRepository = likeRepository;
        this.unLikeRepository = unLikeRepository;
        this.memberRepository = memberRepository;
    }

    public Shoot createShoot(ShootDto shootDto) {
        Goal goal = goalRepository.getOne(shootDto.getGid());
        Member member = memberRepository.findOneByMid(shootDto.getMid());

        // exceed 계산
        boolean isExceed = false;
        LocalDate startDate = goal.getStartDate();
        LocalDate today = LocalDate.now();
        LocalDate date;

        // 하루에 여러번 한 경우
        if (shootRepository.countAllByMakerAndDateAndIsExceeded(member, today, false) > 0) isExceed = true;

        if (goal.getProgressCheckType().getPctId() == 1) {
            // 주 N회를 초과한 경우
            int doneCount = 0;
            int days = Period.between(startDate, today).getDays() % 7;

            for (int i = 1; i < days; i++) {
                date = today.minusDays(i);
                if (shootRepository.countAllByMakerAndDateAndIsExceeded(member, date, false) > 0) doneCount++;
            }

            if (doneCount >= goal.getProgressCheckCount()) isExceed = true;
        }

        // 월 1, 일 7
        if (goal.getProgressCheckType().getPctId() == 2) {
            int todayOfWeek = today.getDayOfWeek().getValue();
            int checkCount = goal.getProgressCheckCount();

            checkCount = checkCount >>> (todayOfWeek - 1);
            if ((checkCount & 1) == 0) isExceed = true;
        }

        Shoot shoot = Shoot.builder()
                .goal(goal)
                .shootName(shootDto.getShootName())
                .date(LocalDate.now())
                .maker(member)
                .likeCount(0)
                .unLikeCount(0)
                .isExceeded(isExceed)
                .build();
        shoot = shootRepository.save(shoot);

        String text = shootDto.getText();
        if (text != null) {
            shootConfirmRepository.save(ShootConfirm.builder()
                    .shoot(shoot)
                    .shootConfirmType(ShootConfirmType.TEXT)
                    .content(text)
                    .build());
        }


        Long baseTime = shootDto.getBaseTime();
        Long time = shootDto.getTime();
        if (baseTime != null) {
            shootConfirmRepository.save(ShootConfirm.builder()
                    .shoot(shoot)
                    .shootConfirmType(ShootConfirmType.BASE_TIMER)
                    .content(time + "/" + baseTime)
                    .build());
        } else if (time != null) {
            shootConfirmRepository.save(ShootConfirm.builder()
                    .shoot(shoot)
                    .shootConfirmType(ShootConfirmType.TIMER)
                    .content(String.valueOf(time))
                    .build());
        }

        return shoot;
    }

    public void uploadImage(String sid, MultipartFile file) {
        Shoot shoot = shootRepository.findOneBySid(Long.valueOf(sid));

        ShootConfirm shootConfirm = ShootConfirm.builder()
                .shootConfirmType(ShootConfirmType.PICTURE)
                .shoot(shoot)
                .build();
        shootConfirm = shootConfirmRepository.save(shootConfirm);

        String fileName = shootConfirm.getCheckId() + ".jpg";
        storageService.uploadFile(fileName, file);
        shootConfirm.setContent(fileName);
        shootConfirmRepository.save(shootConfirm);
    }

    public void deleteShoot(ShootDto shootDto) {
        Shoot shoot = shootRepository.findOneBySid(shootDto.getSid());
        shootRepository.delete(shoot);
    }

    //골 타임라인 paging read
    public List<ShootAndLikeDto> readShootByGoal(Long mid, Goal goal, Pageable pageable) {
//		Pageable pageable = PageRequest.of(start, end); //현재페이지, 조회할 페이지수, 정렬정보
        Member member = memberRepository.findOneByMid(mid);
        Page<Shoot> result = shootRepository.findByGoal(goal, pageable);
        System.out.println("페이징결과 " + result);
        List<Shoot> shootList = result.getContent();

        List<ShootAndLikeDto> shootAndLikeList = new ArrayList<>();
        for (Shoot shoot : shootList) {

            ShootAndLikeDto shootAndLike = ShootAndLikeDto.builder().shoot(shoot).build();
            //해당 멤버가 해당 슛 좋아요 누른 상태
            if (likeRepository.findOneByMemberAndShoot(member, shoot) != null)
                shootAndLike.setLikeBoolean(true);
            else
                shootAndLike.setLikeBoolean(false);
            //해당 멤버가 해당 슛 싫어요 누른 상태
            if (unLikeRepository.findOneByMemberAndShoot(member, shoot) != null)
                shootAndLike.setUnLikeBoolean(true);
            else
                shootAndLike.setUnLikeBoolean(false);

            shootAndLikeList.add(shootAndLike);
        }

        return shootAndLikeList;
    }

    public ShootAndLikeDto upLikeCount(Long sid, Long mid) {
        Shoot shoot = shootRepository.findOneBySid(sid);
        Member member = memberRepository.findOneByMid(mid);

        if (likeRepository.findOneByMemberAndShoot(member, shoot) != null) {
            System.out.println("이미 좋아요가 눌린 상태입니다.");
            return null;
        }

        if (shoot == null) return null;
        Integer count = shoot.getLikeCount();
        count++;
        shoot.setLikeCount(count);

        LikeEntity like = LikeEntity.builder()
                .shoot(shoot).member(member)
                .build();
        LikeEntity afterLike = likeRepository.save(like);
        Shoot afterShoot = shootRepository.save(shoot);
        ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).likeBoolean(true).build();

        //싫어요가 눌린 상태이면
        if (unLikeRepository.findOneByMemberAndShoot(member, shoot) != null)
            shootAndLikeDto.setUnLikeBoolean(true);

        return shootAndLikeDto;
    }

    public ShootAndLikeDto downLikeCount(Long sid, Long mid) {
        Shoot shoot = shootRepository.findOneBySid(sid);
        Member member = memberRepository.findOneByMid(mid);
        if (shoot == null) return null;
        Integer count = shoot.getLikeCount();
        count--;
        shoot.setLikeCount(count);

        LikeEntity afterLike = likeRepository.findOneByMemberAndShoot(member, shoot);
        likeRepository.delete(afterLike);
        Shoot afterShoot = shootRepository.save(shoot);
        ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).likeBoolean(false).build();

        //싫어요가 눌린 상태이면
        if (unLikeRepository.findOneByMemberAndShoot(member, shoot) != null)
            shootAndLikeDto.setUnLikeBoolean(true);

        return shootAndLikeDto;
    }

    public ShootAndLikeDto upUnLikeCount(Long sid, Long mid) {
        Shoot shoot = shootRepository.findOneBySid(sid);
        Member member = memberRepository.findOneByMid(mid);

        if (unLikeRepository.findOneByMemberAndShoot(member, shoot) != null) {
            System.out.println("이미 싫어요가 눌린 상태입니다.");
            return null;
        }

        if (shoot == null) return null;
        Integer count = shoot.getUnLikeCount();
        count++;
        shoot.setUnLikeCount(count);

        UnLikeEntity unLike = UnLikeEntity.builder()
                .shoot(shoot).member(member)
                .build();
        UnLikeEntity afterUnLike = unLikeRepository.save(unLike);
        Shoot afterShoot = shootRepository.save(shoot);
        ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).unLikeBoolean(true).build();

        //좋아요가 눌린 상태이면
        if (likeRepository.findOneByMemberAndShoot(member, shoot) != null)
            shootAndLikeDto.setLikeBoolean(true);

        return shootAndLikeDto;
    }

    public ShootAndLikeDto downUnLikeCount(Long sid, Long mid) {
        Shoot shoot = shootRepository.findOneBySid(sid);
        Member member = memberRepository.findOneByMid(mid);
        if (shoot == null) return null;
        Integer count = shoot.getUnLikeCount();
        count--;
        shoot.setUnLikeCount(count);

        UnLikeEntity afterLike = unLikeRepository.findOneByMemberAndShoot(member, shoot);
        unLikeRepository.delete(afterLike);
        Shoot afterShoot = shootRepository.save(shoot);
        ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).unLikeBoolean(false).build();

        //좋아요가 눌린 상태이면
        if (likeRepository.findOneByMemberAndShoot(member, shoot) != null)
            shootAndLikeDto.setLikeBoolean(true);

        return shootAndLikeDto;
    }
}