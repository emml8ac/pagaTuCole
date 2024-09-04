package com.pagatucole.PagaTuCole.servicios;

import com.pagatucole.PagaTuCole.modelo.SecurityUser;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import com.pagatucole.PagaTuCole.repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioUsuario implements UserDetailsService {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repositorioUsuario.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new SecurityUser(user);
    }
    public void actualizarPasswordPorDni(String username) {
        // Encuentra el usuario con el mismo id_usuario que el DNI
        Usuario usuario = repositorioUsuario.findByUsername(username);

        if (usuario != null) {
            // Verifica si la contraseña no está hasheada (ejemplo: no empieza con $2)
            if (!usuario.getPassword().startsWith("$2")) {
                // Hashea la contraseña y actualiza el usuario
                String hashedPassword = passwordEncoder.encode(usuario.getPassword());
                usuario.setPassword(hashedPassword);
                repositorioUsuario.save(usuario);
            }
        }
}

    public Usuario findByDni(String dni) {
        return repositorioUsuario.findByuserid(dni);
    }
}
