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
public class InviteInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long inviteId;
	@OneToOne
    @JoinColumn(name = "mid")
	private Member fromMember;
	@OneToOne
    @JoinColumn(name = "gid")
	private Goal goal;
}