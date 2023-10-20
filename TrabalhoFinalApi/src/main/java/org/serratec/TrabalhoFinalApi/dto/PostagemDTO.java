package org.serratec.TrabalhoFinalApi.dto;

public class PostagemDTO {
	private Long id;
	private String conteudoPostagem;
	private String dataPostagem;
	private String autorNome;
	private String autorSobrenome;
	private String autorEmail;

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

	public String getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(String dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public String getAutorNome() {
		return autorNome;
	}

	public void setAutorNome(String autorNome) {
		this.autorNome = autorNome;
	}

	public String getAutorSobrenome() {
		return autorSobrenome;
	}

	public void setAutorSobrenome(String autorSobrenome) {
		this.autorSobrenome = autorSobrenome;
	}

	public String getAutorEmail() {
		return autorEmail;
	}

	public void setAutorEmail(String autorEmail) {
		this.autorEmail = autorEmail;
	}
}
