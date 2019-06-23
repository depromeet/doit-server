package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PenaltyCheckType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pctId;
	private String description; //설명
	
}
