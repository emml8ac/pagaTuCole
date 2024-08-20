package com.pagatucole.PagaTuCole.repositorio;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}
