package com.server.doit.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import net.bytebuddy.asm.Advice.Local;

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
}
