package org.serratec.TrabalhoFinalApi.dto;

import java.util.Set;


import org.serratec.TrabalhoFinalApi.model.Postagem;

public class UsuarioDTO {

	private Long id;

	private String nome;

	private String email;

	private Set<Postagem> postagem;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String email, Set<Postagem> postagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.postagem = postagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(Set<Postagem> postagem) {
		this.postagem = postagem;
	}

}
