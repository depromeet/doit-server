package com.server.doit.domain.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.server.doit.domain.dto.GoalAndMembersDto;
import com.server.doit.domain.dto.MemberDto;
import com.server.doit.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.doit.domain.dto.GoalDto;
import com.server.doit.domain.dto.GoalResultDto;
import com.server.doit.domain.entity.Goal;
import com.server.doit.domain.entity.InviteInfo;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.entity.Participant;
import com.server.doit.domain.entity.ProgressCheckType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GoalService {
	private final InviteInfoRepository inviteInfoRepository;
    private final GoalRepository goalRepository;
    private final ProgressCheckTypeRepository progressCheckTypeRepository;
    private final MemberRepository memberRepository;
    private final ParticipantRepository participantRepository;
    private final ShootRepository shootRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, ProgressCheckTypeRepository progressCheckTypeRepository, MemberRepository memberRepository, ParticipantRepository participantRepository, InviteInfoRepository inviteInfoRepository, ShootRepository shootRepository) {
        this.goalRepository = goalRepository;
        this.progressCheckTypeRepository = progressCheckTypeRepository;
        this.memberRepository = memberRepository;
        this.participantRepository = participantRepository;
        this.inviteInfoRepository = inviteInfoRepository;
        this.shootRepository = shootRepository;
    }

    public Goal createGoal(GoalDto goalDto) {
        Goal goal = getGoal(goalDto);

        if (goal == null) {
            log.error("Fail to create goal");
            return null;
        }

        goal = goalRepository.save(goal);
        Member member = memberRepository.getOne(goalDto.getMid());

        Participant participant = Participant.builder()
                .goal(goal)
                .member(member)
                .isHost(true)
                .build();

        if (participant == null) {
            log.error("Fail to create participant");
            return null;
        }

        participantRepository.save(participant);

        return goal;
    }

    public GoalAndMembersDto getGoalAndMembers(Long gid) {
        Goal goal = goalRepository.findOneByGid(gid);
        if (goal == null) {
            log.error("Fail to find goal");
            return null;
        }

        List<Participant> participantList = participantRepository.findAllByGoal(goal);
        List<MemberDto> memberDtos = participantList.stream()
                .map(Participant::getMember)
                .map(member -> MemberDto.builder()
                        .mid(member.getMid())
                        .name(member.getName())
                        .imageUrl(member.getProfileImgUrl())
                        .build())
                .collect(Collectors.toList());

        memberDtos.forEach(memberDto -> memberDto.setProgressRate(calcProgressRate(memberDto.getMid(), goal)));
        memberDtos.sort((o1, o2) -> o2.getProgressRate() - o1.getProgressRate());

        return GoalAndMembersDto.builder()
                .goal(goal)
                .memberDtoList(memberDtos)
                .build();
    }

    private Goal getGoal(GoalDto goalDto) {
        ProgressCheckType progressCheckType = getProgressCheckType(goalDto.getProgressType());

        if (progressCheckType == null) {
            log.error("Fail to get progress check type");
            return null;
        }

        return Goal.builder()
                .goalName(goalDto.getName())
                .category(goalDto.getCategory())
                .startDate(LocalDate.ofEpochDay(goalDto.getStartDate()))
                .endDate(LocalDate.ofEpochDay(goalDto.getEndDate()))
                .penalty(goalDto.getPenalty())
                .progressCheckType(progressCheckType)
                .progressCheckCount(goalDto.getProgressCount())
                .themeColor(goalDto.getColor())
                .timerCheck(goalDto.getTimerCheck())
                .build();
    }

    private ProgressCheckType getProgressCheckType(int type) {
        return progressCheckTypeRepository.getOne((long) type);
    }

    public List<Goal> getGoalList(Long mid) {
        Member member = memberRepository.getOne(mid);
        List<Participant> participantList = participantRepository.findAllByMember(member);
        List<Goal> goalList = new ArrayList<>();

        for (Participant participant : participantList) {
            Goal goal = participant.getGoal();
            goal.setProgressRate(calcProgressRate(mid, goal));
            goalList.add(goal);
        }

        return goalList;
    }

  
    public List<GoalResultDto> finishGoalResult(Long gid) {
    	Goal goal = goalRepository.findOneByGid(gid);
    	if( !checkGoalIsEnd(gid) ) return null;
    	List<Goal> goalList = new ArrayList<>();
    	List<GoalResultDto> goalResultDtoList = new ArrayList<>();
    	Integer successCount=0;
    	int sumPenalty = 0;
    	if(goal == null) return null;
    	List<Participant> participantList = participantRepository.findAllByGoal(goal);
    	
    	for (Participant participant : participantList) {
            Member member = participant.getMember();
            GoalResultDto goalResultDto = GoalResultDto.builder().member(member).build();
            
            Integer progressRate = calcProgressRate(member.getMid(), goal);
            goal.setProgressRate(progressRate);
            System.out.println("check"+goal.getProgressRate());
            if(goal.getProgressRate() < 80) { // 진행률이 80퍼가 안되면
            	float penaltyPercent = (float) ((100-goal.getProgressRate())/100.0);
            	int penalty = (int) (goal.getPenalty()*penaltyPercent);
            	sumPenalty+=penalty;
            	Integer money = penalty * -1;
            	goalResultDto.setMoney(money);
            	goalResultDto.setSuccess(false);
            }
            else {
            	goalResultDto.setSuccess(true);
            	successCount++;
            }
            goalResultDtoList.add(goalResultDto);
        }
    	Integer money = 0;
    	if(participantRepository.countAllByGoal(goal) == successCount | sumPenalty==0 | successCount==0) money = goal.getPenalty();
    	else {
    	money = sumPenalty/successCount;
    	}
    	for (GoalResultDto goalResultDto : goalResultDtoList) {
    		if(goalResultDto.success)
    			goalResultDto.setMoney(money);
		}
    	return goalResultDtoList;
    }
    
    public boolean checkGoalIsEnd(Long gid) {
    	Goal goal = goalRepository.findOneByGid(gid);
    	if(goal.getEndDate().isEqual(LocalDate.now()) ) return true;
    	return false;
    }
    
    private int calcProgressRate(Long mid, Goal goal) {
        int baseCount, doneCount, res;
        int pctId = goal.getProgressCheckType().getPctId().intValue();
        LocalDate today = LocalDate.now();
        LocalDate startDate = goal.getStartDate();
        Member member = memberRepository.getOne(mid);

        doneCount = shootRepository.countAllByGoalAndMakerAndIsExceeded(goal, member, false);
        baseCount = 10;
        switch (pctId) {
            case 1:
                baseCount = (Period.between(startDate, today).getDays() / 7 + 1) * goal.getProgressCheckCount();
                break;
            case 2:
                int progressCheckCount = goal.getProgressCheckCount();
                int checkCount = 0;

                for (int i = 0; i < 7; i++) {
                    if ((progressCheckCount & 1) > 0) checkCount++;
                    progressCheckCount = progressCheckCount >>> 1;
                }

                baseCount = (Period.between(startDate, today).getDays() / 7) * checkCount;
                int days = Period.between(startDate, today).getDays() % 7;

                for (int i = 0; i < days; i++) {
                    LocalDate date = today.minusDays(i);
                    int dayOfWeek = date.getDayOfWeek().getValue();

                    dayOfWeek  = 1 << (dayOfWeek - 1);

                    if ((goal.getProgressCheckCount() & dayOfWeek) > 0) baseCount++;
                }

                break;
            case 3:
                baseCount = Period.between(goal.getStartDate(), today).getDays();
                break;
        }

        res = (int) ((doneCount / (double)baseCount) * 100);

        return res;
    }
    
    //초대
    public Long invite(Long mid,Long gid){
    	Member fromMember = memberRepository.findOneByMid(mid);
    	Goal goal = goalRepository.findOneByGid(gid);
    	InviteInfo inviteInfo = InviteInfo.builder()
    			.fromMember(fromMember).goal(goal)
    			.build();
    	InviteInfo result = inviteInfoRepository.save(inviteInfo);
    	
    	if(result == null) return null;
    	
    	return result.getInviteId();
    }
    
    //골에 참여
    public Participant participateGoal (Long inviteId, Long gid,Long mid) {  //초대코드 , 참가할 골, 참가할 멤버
    	Goal goal = goalRepository.findOneByGid(gid);
    	Member member = memberRepository.findOneByMid(mid);
    	
    	if(goal == null) return null;
    	if(member == null) return null;
    	
    	InviteInfo invite = inviteInfoRepository.findOneByInviteId(inviteId);
    	
    	if(invite.getGoal() != goal) //초대한 골과 현재 참여할 골이 같지 않으면 오류
    		return null;
    	if (participantRepository.findOneByMemberAndGoal(member, goal) != null) return null;  //이미 골에 참여되어 있는 상태임
    	
    	Participant participant = Participant.builder()
                .goal(goal)
                .member(member)
                .isHost(false)
                .build();
    	
    	if (participant == null) {
            log.error("Fail to create participant");
            return null;
        }
    	inviteInfoRepository.delete(invite);
        return participantRepository.save(participant);
    }
}
