package com.mosainfo.mblabapi.security.service;

import com.mosainfo.mblabapi.security.entity.Rol;
import com.mosainfo.mblabapi.security.enums.RolNombre;
import com.mosainfo.mblabapi.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre( rolNombre );
    }

    public void save(Rol rol) {
        rolRepository.save( rol );
    }
}
