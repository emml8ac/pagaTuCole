package com.pagatucole.PagaTuCole.servicios;

import com.pagatucole.PagaTuCole.modelo.SecurityUser;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import com.pagatucole.PagaTuCole.repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class ServicioUsuario implements UserDetailsService {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repositorioUsuario.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new SecurityUser(user);
    }
    public Usuario saveUsuario(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }
}
