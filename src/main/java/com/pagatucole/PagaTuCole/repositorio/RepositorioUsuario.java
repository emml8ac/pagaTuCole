package com.pagatucole.PagaTuCole.repositorio;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario,Long> {
    Usuario findByUsername(String username);
    Usuario findByuserid(String id_usuario);

    @Modifying
    @Query("update Usuario set password = password where username = username")
    void updatePass(String username, String password);
}
