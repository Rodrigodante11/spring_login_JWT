package curso.api.rest.controller;

import curso.api.rest.dto.TelefoneDTO;
import curso.api.rest.dto.UsuarioDTO;
import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class IndexController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping( value = "/buscar/{id}" , produces = "application/json")
    @Transactional
    public ResponseEntity buscarUsuarioPorID(@PathVariable (value = "id")  Long id){

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            Usuario usuarioEntity = usuario.get();

            usuarioDTO.setId(usuarioEntity.getId());
            usuarioDTO.setLogin(usuarioEntity.getLogin());
            usuarioDTO.setNome(usuarioEntity.getNome());

            List<TelefoneDTO> telefonesDTO = usuarioEntity.getTelefones().stream()
                    .map(telefone -> {
                        TelefoneDTO telefoneDTO = new TelefoneDTO();
                        telefoneDTO.setId(telefone.getId());
                        telefoneDTO.setNumero(telefone.getNumero());
                        return telefoneDTO;
                    })
                    .collect(Collectors.toList());

            usuarioDTO.setTelefones(telefonesDTO);

            return new ResponseEntity(usuarioDTO, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping( value = "/buscar" , produces = "application/json")
    @Transactional
    public ResponseEntity buscarUsuario(){

        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> {
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setId(usuario.getId());
                    usuarioDTO.setLogin(usuario.getLogin());
                    usuarioDTO.setNome(usuario.getNome());

                    List<TelefoneDTO> telefonesDTO = usuario.getTelefones().stream()
                            .map(telefone -> {
                                TelefoneDTO telefoneDTO = new TelefoneDTO();
                                telefoneDTO.setId(telefone.getId());
                                telefoneDTO.setNumero(telefone.getNumero());
                                return telefoneDTO;
                            })
                            .collect(Collectors.toList());

                    usuarioDTO.setTelefones(telefonesDTO);

                    return usuarioDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);

    }

    @PostMapping(value ="/salvar", produces = "application/json")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody  Usuario usuario) {

        Usuario usuarioSalvo =   usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @PutMapping(value = "/atualizar", produces = "application/json")
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
