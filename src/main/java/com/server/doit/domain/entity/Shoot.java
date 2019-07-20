package com.server.doit.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@OneToMany(mappedBy = "shoot")
    @JsonManagedReference
	private List<ShootConfirm> shootConfirmList;
	private boolean isExceeded = false;

	@JsonProperty
    private long epochDate() {
        return date.toEpochDay();
    }
}