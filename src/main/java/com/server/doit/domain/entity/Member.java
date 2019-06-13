package com.server.doit.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long mid;
	//카카오 인증을 통해 발급받을 아이디
	private String kakaoId;
	private String name;
	private String profileImgUrl;
}