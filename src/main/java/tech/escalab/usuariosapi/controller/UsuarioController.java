package tech.escalab.usuariosapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.usuariosapi.model.dto.UsuarioRequest;
import tech.escalab.usuariosapi.model.entity.Usuario;
import tech.escalab.usuariosapi.service.impl.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    UsuarioServiceImpl usuarioService;
    public UsuarioController(UsuarioServiceImpl usuarioService){
        this.usuarioService = usuarioService;
    }
    public static List<UsuarioRequest> usuarios = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<UsuarioRequest>> getUsuarios(){
        usuarios = usuarioService.getUsuarios();
        if(usuarios.size()>0){
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRequest> getUsuario(@Valid @PathVariable("id") int id){
        UsuarioRequest usuario = usuarioService.getUsuario(id);

        if(usuario==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<UsuarioRequest> insertarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        UsuarioRequest usuario = usuarioService.insertarUsuario(usuarioRequest);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable("id") int id){
        UsuarioRequest usuario = usuarioService.eliminarUsuario(id);

        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity actualizarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        UsuarioRequest usuario = usuarioService.actualizarUsuario(usuarioRequest);
        if(usuario == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

}
