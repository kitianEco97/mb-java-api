package com.mosainfo.mblabapi.security.repository;


import com.mosainfo.mblabapi.security.entity.Rol;
import com.mosainfo.mblabapi.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
