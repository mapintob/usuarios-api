package tech.escalab.usuariosapi.service.impl;

import org.springframework.stereotype.Service;
import tech.escalab.usuariosapi.model.dto.UsuarioRequest;
import tech.escalab.usuariosapi.model.entity.Telefono;
import tech.escalab.usuariosapi.model.entity.Usuario;
import tech.escalab.usuariosapi.model.repository.UsuarioRepository;
import tech.escalab.usuariosapi.service.IUsuarioService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    UsuarioRepository usuarioRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioRequest> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioRequest> usuariosDto = new ArrayList<>();

        for (Usuario usuario: usuarios) {
            usuariosDto.add(UsuarioRequest.mapToDto(usuario));
        }
        
        return usuariosDto;
    }

    @Override
    public UsuarioRequest getUsuario(int id) {
        Usuario usuario;
        if (usuarioRepository.existsById(id)){
            usuario =  usuarioRepository.findById(id).get();
            return new UsuarioRequest(usuario.getNombre(), usuario.getCorreo(), usuario.getClave(), usuario.getTelefonos(), usuario.getCreado(), usuario.getModificado(),usuario.getIsActive(), usuario.getUsuarioId());
        }
        return null;
    }

    @Override
    public UsuarioRequest insertarUsuario(UsuarioRequest usuarioRequest) {

        Usuario usuarioEntity = UsuarioRequest.mapToEntity(usuarioRequest);
        //seteamos fechas
        usuarioEntity.setCreado(LocalDateTime.now());
        usuarioEntity.setModificado(LocalDateTime.now());
        usuarioEntity.setIsActive(1);
        Usuario save = usuarioRepository.save(usuarioEntity);

        UsuarioRequest response = new UsuarioRequest();
        response.setUsuarioId(save.getUsuarioId());
        response.setIsActive(save.getIsActive());
        response.setCreado(save.getCreado());
        response.setModificado(save.getModificado());
        response.setNombre(save.getNombre());
        response.setCorreo(save.getCorreo());
        response.setClave(save.getClave());
        response.setTelefonos(save.getTelefonos());

        return response;
    }

    @Override
    public UsuarioRequest eliminarUsuario(int id) {
        Usuario usuario;
        if (usuarioRepository.existsById(id)){
            usuario = usuarioRepository.findById(id).get();
            usuarioRepository.deleteById(id);
            return new UsuarioRequest(usuario.getNombre(),usuario.getCorreo(),usuario.getClave(),usuario.getTelefonos(),usuario.getCreado(), usuario.getModificado(),usuario.getIsActive(),usuario.getUsuarioId());
        }
        return null;
    }
    @Override
    public UsuarioRequest actualizarUsuario(UsuarioRequest usuarioRequest) {

        Usuario usuarioEntity = UsuarioRequest.mapToEntity(usuarioRequest);
        System.out.println("id que llega: "+usuarioEntity.getUsuarioId());
        if (usuarioRepository.existsById(usuarioRequest.getUsuarioId())){
            System.out.println("entro al if");
            Usuario usuario =  usuarioRepository.findById(usuarioEntity.getUsuarioId()).get();

            if(!usuarioEntity.getNombre().isEmpty()){
                usuario.setNombre(usuarioEntity.getNombre());
            }
            if(!usuarioEntity.getClave().isEmpty()){
                usuario.setClave(usuarioEntity.getClave());
            }
            if(!usuarioEntity.getCorreo().isEmpty()){
                usuario.setCorreo(usuarioEntity.getCorreo());
            }

            if(usuarioEntity.getTelefonos().size()>0) {
                Boolean agregar = true;
                for (Telefono telefonoBD : usuario.getTelefonos()) {
                    for (Telefono telefonoET : usuarioEntity.getTelefonos()) {
                        if (telefonoET.equals(telefonoBD)) {
                            agregar = false;
                            break;
                        }
                    }
                    if (agregar) {
                        usuarioEntity.getTelefonos().add(telefonoBD);
                    }
                }
            }else{
                usuarioEntity.setTelefonos(usuario.getTelefonos());
            }

            usuarioEntity.setModificado(LocalDateTime.now());
            usuarioEntity.setCreado(usuario.getCreado());
            usuarioEntity.setIsActive(usuario.getIsActive());

            Usuario save = usuarioRepository.save(usuarioEntity);

            UsuarioRequest response = new UsuarioRequest();
            response = response.mapToDto(save);

            return response;
        }
        System.out.println("no al if");
        return null;
    }
}
