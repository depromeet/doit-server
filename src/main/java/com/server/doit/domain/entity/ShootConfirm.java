package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ShootConfirm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long checkId;
	ShootConfirmType shootConfirmType;
	String content;
	@ManyToOne
	@JoinColumn(name = "sid")
	@JsonBackReference
	private Shoot shoot;
}