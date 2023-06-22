package tech.escalab.usuariosapi.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.escalab.usuariosapi.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

}
