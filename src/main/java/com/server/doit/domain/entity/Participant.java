package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.server.doit.domain.entity.Member.MemberBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
	@ManyToOne
	@JoinColumn(name = "mid")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "gid")
	private Goal goal;
}
