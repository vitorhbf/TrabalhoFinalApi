package org.serratec.TrabalhoFinalApi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuario_relacionamento")
public class UsuarioRelacionamento {

	@EmbeddedId
	private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();

	@CreationTimestamp
	@Column(name = "data_inicio_Seguimento")
	private LocalDate dataInicioSeguimento;
	
	public UsuarioRelacionamento() {
	}

	public UsuarioRelacionamento(LocalDate dataInicioSeguimento, Usuario usuarioSeguidor, Usuario usuarioSeguido) {
		this.id.setUsuarioSeguido(usuarioSeguidor);
		this.id.setUsuarioSeguidor(usuarioSeguido);
		this.dataInicioSeguimento = dataInicioSeguimento;
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
