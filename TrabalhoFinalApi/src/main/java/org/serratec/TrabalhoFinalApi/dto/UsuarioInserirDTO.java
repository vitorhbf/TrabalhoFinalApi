package org.serratec.TrabalhoFinalApi.dto;

import java.util.Set;

import org.serratec.TrabalhoFinalApi.model.Postagem;

public class UsuarioInserirDTO {

	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;

	private Set<Postagem> perfis;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Set<Postagem> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Postagem> perfis) {
		this.perfis = perfis;
	}
}
