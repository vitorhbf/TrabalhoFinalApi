package org.serratec.TrabalhoFinalApi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_postagem")
public class UsuarioPostagem {

	@EmbeddedId
	private UsuarioPostagemPK id = new UsuarioPostagemPK();

	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	public UsuarioPostagem() {
	}

	public UsuarioPostagem(UsuarioPostagemPK id, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.dataCriacao = LocalDate.now();
	}

	public UsuarioPostagemPK getId() {
		return id;
	}

	public void setId(UsuarioPostagemPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
