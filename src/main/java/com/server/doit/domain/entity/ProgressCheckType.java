package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class ProgressCheckType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pctId;
	private String description; //설명
}
