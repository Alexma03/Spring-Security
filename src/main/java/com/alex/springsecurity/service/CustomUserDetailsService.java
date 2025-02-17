package com.alex.springsecurity.service;

import com.alex.springsecurity.config.CustomUserDetails;
import com.alex.springsecurity.model.Perfil;
import com.alex.springsecurity.model.Usuario;
import com.alex.springsecurity.repository.PerfilRepository;
import com.alex.springsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private PerfilRepository perfilRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new CustomUserDetails(usuario);
    }

    public void asignarRolAUsuario(String username, int idPerfil) {
        Usuario usuario = userRepo.findByUsername(username);
        Perfil perfil = perfilRepo.findById(idPerfil).orElseThrow();
        usuario.getPerfiles().add(perfil);
        userRepo.save(usuario);
    }

}
