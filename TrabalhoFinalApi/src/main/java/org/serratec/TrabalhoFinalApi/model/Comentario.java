package org.serratec.TrabalhoFinalApi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	@ApiModelProperty(value = "Identificador único do comentário")
	private Long id;

	@NotNull(message = "Preencha a data do comentário")
	@Column(name = "data_inicio_seguimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "Data de início do comentário", required = true)
	private Date dataComentario;

	@NotBlank(message = "Preencha o conteudo do comentário ")
	@Column(name = "conteudo_comentario", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ApiModelProperty(value = "Conteúdo do comentário", required = true)
	private String conteudoComentario;

	@ManyToOne
	@JoinColumn(name = "id_postagem")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ApiModelProperty(value = "Postagem associada ao comentário", required = true)
	private Postagem postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudoComentario() {
		return this.conteudoComentario;
	}

	public Date getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public void setConteudoComentario(String conteudoComentario) {
		this.conteudoComentario = conteudoComentario;
	}
}
