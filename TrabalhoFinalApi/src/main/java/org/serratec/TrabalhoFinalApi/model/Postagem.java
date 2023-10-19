package org.serratec.TrabalhoFinalApi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postagem")
	private Long id;

	@Column(name = "conteudo_postagem", length = 255)
	private String conteudoPostagem;

	@Column(name = "data_postagem")
	private Date dataPostagem;

	@Column(name = "autor", length = 255)
	private String autor;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
