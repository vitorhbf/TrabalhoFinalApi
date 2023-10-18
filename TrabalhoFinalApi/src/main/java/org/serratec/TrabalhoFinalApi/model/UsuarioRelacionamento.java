package org.serratec.TrabalhoFinalApi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_relacionamento")
public class UsuarioRelacionamento {

    @EmbeddedId
    private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();

    @Column(name = "data_criacao")
    private LocalDate dataInicioSeguimento;

    public UsuarioRelacionamento() {
    }

    public UsuarioRelacionamento(Usuario usuarioSeguidor, Usuario usuarioSeguido) {
        this.id.setUsuarioSeguido(usuarioSeguidor);
        this.id.setUsuarioSeguidor(usuarioSeguido);
        this.dataInicioSeguimento = LocalDate.now();
    }

    public UsuarioRelacionamentoPK getId() {
        return id;
    }

    public void setId(UsuarioRelacionamentoPK id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataInicioSeguimento;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataInicioSeguimento = dataCriacao;
    }

}
