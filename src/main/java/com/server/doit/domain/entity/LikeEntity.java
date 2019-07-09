package com.server.doit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lid;
	@OneToOne
    @JoinColumn(name = "mid")
	private Member member;
	@OneToOne
    @JoinColumn(name = "sid")
	private Shoot shoot;
}