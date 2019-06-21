package com.server.doit.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Shoot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
	private String shootName;
	private LocalDate date;
	private Integer likeCount;
}