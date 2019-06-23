package com.server.doit.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Goal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long gid;
	private String goalName;
	private String categorie;
	private LocalDate stDate;
	private LocalDate endDate;
	private String themaColor;
	private Integer penalty;
	private Integer penaltyCheckCount;
	private Boolean timerCheck;
	
	@OneToOne
	@JoinColumn(name="pctId")
	private PenaltyCheckType penaltyCheckType;
	
}
