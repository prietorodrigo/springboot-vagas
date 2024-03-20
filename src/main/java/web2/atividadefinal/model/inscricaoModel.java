package web2.atividadefinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

@Entity
@Table(name="inscricao")
public class inscricaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    private String imagem;

    @NotBlank
    private String linkedin;

    @ManyToOne
    private vagaModel vaga;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public vagaModel getVaga() {
        return vaga;
    }

    public void setVaga(vagaModel vaga) {
        this.vaga = vaga;
    }
}
