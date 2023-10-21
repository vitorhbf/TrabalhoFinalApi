package org.serratec.TrabalhoFinalApi.dto;

public class UsuarioRelacionamentoDTO {

	private UsuarioSeguidorDTO seguidor;
	private UsuarioSeguidoDTO seguido;

	public UsuarioSeguidorDTO getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(UsuarioSeguidorDTO seguidor) {
		this.seguidor = seguidor;
	}

	public UsuarioSeguidoDTO getSeguido() {
		return seguido;
	}

	public void setSeguido(UsuarioSeguidoDTO seguido) {
		this.seguido = seguido;
	}
}
