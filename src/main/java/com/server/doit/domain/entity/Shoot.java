package com.server.doit.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.doit.domain.dto.ShootDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shoot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
	private String shootName;
	@JsonIgnore
	private LocalDate date;
	private Integer likeCount;
	private Integer unLikeCount;
	@ManyToOne
	@JoinColumn(name = "gid")
	@JsonIgnore
	private Goal goal;
	@OneToOne
	@JoinColumn(name = "mid")
	private Member maker;

	@JsonProperty
    private long epochDate() {
        return date.toEpochDay();
    }
}