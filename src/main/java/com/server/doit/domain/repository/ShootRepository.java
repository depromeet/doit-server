package com.server.doit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Shoot;

public interface ShootRepository extends JpaRepository<Shoot, Long> {

}
