package curso.api.rest.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UsuarioDTO implements Serializable {

    private Long id;
    private String login;
    private String nome;

    private List<TelefoneDTO> telefones;

}
