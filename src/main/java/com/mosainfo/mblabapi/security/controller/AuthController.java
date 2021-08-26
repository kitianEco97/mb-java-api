package com.mosainfo.mblabapi.security.controller;
import com.mosainfo.mblabapi.dto.Mensaje;
import com.mosainfo.mblabapi.security.dto.JwtDto;
import com.mosainfo.mblabapi.security.dto.LoginUsuario;
import com.mosainfo.mblabapi.security.dto.NuevoUsuario;
import com.mosainfo.mblabapi.security.entity.Rol;
import com.mosainfo.mblabapi.security.entity.Usuario;
import com.mosainfo.mblabapi.security.enums.RolNombre;
import com.mosainfo.mblabapi.security.jwt.JwtProvider;
import com.mosainfo.mblabapi.security.service.RolService;
import com.mosainfo.mblabapi.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity( new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST );
        if (usuarioService.existsByNombreUsuario( nuevoUsuario.getNombreUsuario() ))
            return new ResponseEntity( new Mensaje( "ese nombre ya éxiste" ), HttpStatus.BAD_REQUEST );
        if (usuarioService.existsByEmail( nuevoUsuario.getEmail() ))
            return new ResponseEntity( new Mensaje( "ese email ya éxiste" ), HttpStatus.BAD_REQUEST );
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode( nuevoUsuario.getPassword() ));
        Set<Rol> roles = new HashSet<>();
        roles.add( rolService.getByRolNombre( RolNombre.ROLE_USER ).get() );
        if (nuevoUsuario.getRoles().contains( "admin" ))
            roles.add( rolService.getByRolNombre( RolNombre.ROLE_ADMIN ).get() );
        usuario.setRoles( roles );
        usuarioService.save( usuario );
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity( new Mensaje( "Campos mal puestos o email invalido" ), HttpStatus.BAD_REQUEST );
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( loginUsuario.getNombreUsuario(), loginUsuario.getPassword() ) );
        SecurityContextHolder.getContext().setAuthentication( authentication );
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto( jwt );
        return new ResponseEntity( jwtDto, HttpStatus.OK );
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>( jwt, HttpStatus.OK );
    }
}
