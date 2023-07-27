package curso.api.rest.controller;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class IndexController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping( value = "/buscar/{id}" , produces = "application/json")
    public ResponseEntity buscarUsuarioPorID(@PathVariable (value = "id")  Long id){

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return new ResponseEntity(usuario.get() , HttpStatus.OK);
    }

    @GetMapping( value = "/buscar/" , produces = "application/json")
    public ResponseEntity<List<Usuario>> buscarUsuario(){

        List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);

    }

    @PostMapping(value ="/salvar/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody  Usuario usuario) {

        Usuario usuarioSalvo =   usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @PutMapping(value = "/atualizar/", produces = "application/json")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuario.getId());
        if (usuarioEncontrado.isPresent()) {
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/deletar/{id}", produces = "application/text")
    public ResponseEntity deletarUsuario(@PathVariable("id") Long id) {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
