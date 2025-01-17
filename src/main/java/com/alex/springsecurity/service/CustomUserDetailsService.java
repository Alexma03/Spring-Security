package com.alex.springsecurity.service;

import com.alex.springsecurity.config.CustomUserDetails;
import com.alex.springsecurity.model.Perfil;
import com.alex.springsecurity.model.User;
import com.alex.springsecurity.repository.PerfilRepository;
import com.alex.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PerfilRepository perfilRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public void asignarRolAUsuario(String username, int idPerfil) {
        User user = userRepo.findByUsername(username);
        Perfil perfil = perfilRepo.findById(idPerfil).orElseThrow(); // Asegúrate de tener un PerfilRepository
        user.getPerfiles().add(perfil);
        userRepo.save(user);
    }

}
