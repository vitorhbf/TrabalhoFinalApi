package org.serratec.TrabalhoFinalApi.dto;

public class ComentarioDTO {
	
	private Long id;
	private String conteudoComentario;
	private String dataComentario;
	private PostagemDTO postagem;
	private UsuarioDTO usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudoComentario() {
		return conteudoComentario;
	}

	public void setConteudoComentario(String conteudoComentario) {
		this.conteudoComentario = conteudoComentario;
	}

	public String getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(String dataComentario) {
		this.dataComentario = dataComentario;
	}

	public PostagemDTO getPostagem() {
		return postagem;
	}

	public void setPostagem(PostagemDTO postagem) {
		this.postagem = postagem;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
