package com.server.doit.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.server.doit.domain.dto.ShootAndLikeDto;
import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.LikeEntity;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.entity.UnLikeEntity;
import com.server.doit.domain.repository.GoalRepository;
import com.server.doit.domain.repository.LikeRepository;
import com.server.doit.domain.repository.MemberRepository;
import com.server.doit.domain.repository.ShootRepository;
import com.server.doit.domain.repository.UnLikeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShootService {
	@Autowired
	private ShootRepository shootRepository;
	@Autowired
	private GoalRepository goalRepository;
	@Autowired 
	private LikeRepository likeRepository;
	@Autowired 
	private UnLikeRepository unLikeRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	public Shoot createShoot(ShootDto shootDto) {
		Long gid = shootDto.getGid();
		Goal goal = goalRepository.findOneByGid(gid);
		Shoot shoot = Shoot.builder()
				.shootName(shootDto.getShootName())
				.goal(goal)
				.date(LocalDate.now())
				.likeCount(0)
				.unLikeCount(0)
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
	
	public ShootAndLikeDto upLikeCount(Long sid,Long mid) {
		Shoot shoot = shootRepository.findOneBySid(sid);
		Member member = memberRepository.findOneByMid(mid);
		
		if(likeRepository.findOneByMemberAndShoot(member, shoot) != null) {
			System.out.println("이미 좋아요가 눌린 상태입니다.");
			return null;
		}
		
		if(shoot == null) return null;
		Integer count= shoot.getLikeCount();
		count++;
		shoot.setLikeCount(count);
			
		LikeEntity like = LikeEntity.builder()
				.shoot(shoot).member(member)
				.build();
		LikeEntity afterLike = likeRepository.save(like);
		Shoot afterShoot = shootRepository.save(shoot);
		ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).likeBoolean(true).build();
		
		//싫어요가 눌린 상태이면
		if(unLikeRepository.findOneByMemberAndShoot(member, shoot) != null)
			shootAndLikeDto.setUnLikeBoolean(true);
		
		return shootAndLikeDto;
	}
	public ShootAndLikeDto downLikeCount(Long sid,Long mid) {
		Shoot shoot = shootRepository.findOneBySid(sid);
		Member member = memberRepository.findOneByMid(mid);
		if(shoot == null) return null;
		Integer count= shoot.getLikeCount();
		count--;
		shoot.setLikeCount(count);
		
		LikeEntity afterLike = likeRepository.findOneByMemberAndShoot(member, shoot);
		likeRepository.delete(afterLike);
		Shoot afterShoot = shootRepository.save(shoot);
		ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).likeBoolean(false).build();
		
		//싫어요가 눌린 상태이면
		if(unLikeRepository.findOneByMemberAndShoot(member, shoot) != null)
			shootAndLikeDto.setUnLikeBoolean(true);
		
		return shootAndLikeDto;
	}
	
	public ShootAndLikeDto upUnLikeCount(Long sid,Long mid) {
		Shoot shoot = shootRepository.findOneBySid(sid);
		Member member = memberRepository.findOneByMid(mid);
		
		if(unLikeRepository.findOneByMemberAndShoot(member, shoot) != null) {
			System.out.println("이미 싫어요가 눌린 상태입니다.");
			return null;
		}
		
		if(shoot == null) return null;
		Integer count= shoot.getUnLikeCount();
		count++;
		shoot.setUnLikeCount(count);
		
		UnLikeEntity unLike = UnLikeEntity.builder()
				.shoot(shoot).member(member)
				.build();
		UnLikeEntity afterUnLike = unLikeRepository.save(unLike);
		Shoot afterShoot = shootRepository.save(shoot);
		ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).unLikeBoolean(true).build();
		
		//좋아요가 눌린 상태이면
		if(likeRepository.findOneByMemberAndShoot(member, shoot) != null)
			shootAndLikeDto.setLikeBoolean(true);
		
		return shootAndLikeDto;
	}
	
	public ShootAndLikeDto downUnLikeCount(Long sid,Long mid) {
		Shoot shoot = shootRepository.findOneBySid(sid);
		Member member = memberRepository.findOneByMid(mid);
		if(shoot == null) return null;
		Integer count= shoot.getUnLikeCount();
		count--;
		shoot.setUnLikeCount(count);
		
		UnLikeEntity afterLike = unLikeRepository.findOneByMemberAndShoot(member, shoot);
		unLikeRepository.delete(afterLike);
		Shoot afterShoot = shootRepository.save(shoot);
		ShootAndLikeDto shootAndLikeDto = ShootAndLikeDto.builder().shoot(afterShoot).unLikeBoolean(false).build();
		
		//좋아요가 눌린 상태이면
		if(likeRepository.findOneByMemberAndShoot(member, shoot) != null)
			shootAndLikeDto.setLikeBoolean(true);
		
		return shootAndLikeDto;
	}
	
//	public Shoot upUnLikeCount(Long sid) {
//		Shoot shoot = shootRepository.findOneBySid(sid);
//		if(shoot == null) return null;
//		Integer count= shoot.getUnLikeCount();
//		count++;
//		shoot.setUnLikeCount(count);
//		
//		return shootRepository.save(shoot);
//	}
//	public Shoot downUnLikeCount(Long sid) {
//		Shoot shoot = shootRepository.findOneBySid(sid);
//		if(shoot == null) return null;
//		Integer count= shoot.getUnLikeCount();
//		count--;
//		shoot.setUnLikeCount(count);
//		
//		return shootRepository.save(shoot);
//	}
}