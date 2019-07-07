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
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
	private String shootName;
//	@JsonIgnore
	private LocalDate date;
	private Integer likeCount;
	@ManyToOne
	@JoinColumn(name = "gid")
	private Goal goal;
	
//	@OneToOne
//	@JoinColumn(name="checkId")
//	private ShootConfirm shootConfirm;
	
//	@JsonProperty
//    private long epochDate() {
//        return date.toEpochDay();
//    }
	
//	public ShootDto toDto() {
//		return ShootDto.builder()
//				.sid(sid).shootName(shootName).date(date).likeCount(likeCount).goal(goal)
//				.build();
//		}
}