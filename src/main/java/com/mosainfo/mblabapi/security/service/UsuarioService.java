package com.mosainfo.mblabapi.security.service;

import com.mosainfo.mblabapi.security.entity.Usuario;
import com.mosainfo.mblabapi.security.repository.UsuarioRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepsitory usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario( nombreUsuario );
    }

    public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail) {
        return usuarioRepository.findByNombreUsuarioOrEmail( nombreOrEmail, nombreOrEmail );
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario( nombreUsuario );
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail( email );
    }

    public void save(Usuario usuario) {
        usuarioRepository.save( usuario );
    }
}
