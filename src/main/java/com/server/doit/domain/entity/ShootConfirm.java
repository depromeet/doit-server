package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ShootConfirm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long checkId;
	ShootConfirmType shootConfirmType;
	String content;
}
