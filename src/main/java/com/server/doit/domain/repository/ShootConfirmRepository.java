package com.server.doit.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.doit.domain.entity.Shoot;
import com.server.doit.domain.entity.ShootConfirm;
import com.server.doit.domain.entity.ShootConfirmType;

public interface ShootConfirmRepository extends JpaRepository<ShootConfirm, Long> {
	public ShootConfirm findOneByShoot(Shoot shoot);
	public List<ShootConfirm> findAllByShoot(Shoot shoot);
	public ShootConfirm findOneByShootConfirmType(ShootConfirmType shootConfirmType);
}
