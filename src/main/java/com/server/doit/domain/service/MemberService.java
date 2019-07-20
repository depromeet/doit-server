package com.server.doit.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.doit.adapter.KakaoAdapter;
import com.server.doit.domain.dto.KakaoUserDto;
import com.server.doit.domain.entity.Member;
import com.server.doit.domain.repository.MemberRepository;
import com.server.doit.exception.ApiFailedException;

@Service
public class MemberService {
	@Autowired
	private KakaoAdapter kakaoAdapter;
	@Autowired
    private MemberRepository memberRepository;
	
	@Transactional(readOnly = true)
    public Member getMemberByMid(Long mid) {
        return memberRepository.findById(mid)
                .orElseThrow(() -> new ApiFailedException("Member is not found.", HttpStatus.NOT_FOUND));
    }
	/**
	 * login 또는 새로운 멤버 등록
	 * @param accessToken
	 * @return Member
	 */
    @Transactional
    public Member getOrCreateMember(String kakaoToken, String firebaseToken) {
        final KakaoUserDto kakaoUserDto = kakaoAdapter.getUserInfo(kakaoToken);
        if (kakaoUserDto == null) {
            throw new ApiFailedException("Failed to get user info from kakao api", HttpStatus.SERVICE_UNAVAILABLE);
        }
        final String kakaoId = kakaoUserDto.getId().toString();
        final String profileImgUrl = kakaoUserDto.getProfileImage();
        final String name = kakaoUserDto.getUserName();
        return memberRepository.findOneByKakaoId(kakaoId)
                .orElseGet(() -> {    //해당 카카오id가 우리 서버에 존재 하지않으면 회원 등록
                	System.out.println("새로운 유저 등록!");
                    final Member member = new Member();
                    member.setKakaoId(kakaoId);
                    member.setProfileImgUrl(profileImgUrl);
                    member.setName(name);
                    member.setFirebaseToken(firebaseToken);
                    return memberRepository.save(member);
                });
    }
    
    public Member refreshFirebaseToken(String mid, String firebaseToken) {
    	Long memberId = Long.parseLong(mid);
    	Member member = memberRepository.findOneByMid(memberId);
    	if(member == null) return null;
    	
    	member.setFirebaseToken(firebaseToken);
    	return memberRepository.save(member);
    }
}