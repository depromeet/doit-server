package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
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
