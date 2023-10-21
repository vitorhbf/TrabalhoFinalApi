package org.serratec.TrabalhoFinalApi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postagem")
	@ApiModelProperty(value = "Identificador único da postagem")
	private Long id;

	@NotBlank(message = "Preencha o conteúdo da postagem")
	@Size(max = 255)
	@Column(name = "conteudo_postagem", nullable = false, length = 255)
	@ApiModelProperty(value = "Conteúdo da postagem", required = true)
	private String conteudoPostagem;

	@NotNull(message = "Preencha a data da postagem ")
	@Column(name = "data_postagem", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "Data da postagem", required = true)
	private Date dataPostagem;

	@NotBlank(message = "Preencha o autor da postagem")
	@Size(max = 255)
	@Column(name = "autor", nullable = false, length = 255)
	@ApiModelProperty(value = "Autor da postagem", required = true)
	private String autor;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ApiModelProperty(value = "Usuário associado à postagem", required = true)
	private Usuario usuario;

	@OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL)
	private List<Comentario> usuarioComentario = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudoPostagem() {
		return conteudoPostagem;
	}

	public void setConteudoPostagem(String conteudoPostagem) {
		this.conteudoPostagem = conteudoPostagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}
}
