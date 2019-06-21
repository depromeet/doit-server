package com.server.doit.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
}
