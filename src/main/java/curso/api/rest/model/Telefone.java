package curso.api.rest.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false ,length = 20)
    private String numero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id" , nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
