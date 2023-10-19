package org.serratec.TrabalhoFinalApi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Long id;

	@Column(name = "data_inicio_seguimento")
	private Date dataInicioSeguimento;

	@ManyToOne
	@JoinColumn(name = "id_postagem")
	private Postagem postagem;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(Date dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

}
