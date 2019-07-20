package com.server.doit.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.server.doit.domain.entity.Goal.GoalBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long mid;
	//카카오 인증을 통해 발급받을 아이디
	private String kakaoId;
	private String name;
	private String profileImgUrl;
	private String firebaseToken;
}