package tech.escalab.usuariosapi.service;

import tech.escalab.usuariosapi.model.dto.UsuarioRequest;
import tech.escalab.usuariosapi.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioRequest> getUsuarios();
    UsuarioRequest getUsuario(int id);
    UsuarioRequest insertarUsuario(UsuarioRequest usuario);
    UsuarioRequest eliminarUsuario(int id);
    UsuarioRequest actualizarUsuario(UsuarioRequest usuario);
}
