package com.server.doit.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
	@ManyToOne
	@JoinColumn(name = "mid")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "gid")
	private Goal goal;
	private boolean isHost = false;
}
