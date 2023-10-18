package org.serratec.TrabalhoFinalApi.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioRelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_seguidor")
	private Usuario usuarioSeguidor;

	@ManyToOne
	@JoinColumn(name = "id_seguido")
	private Usuario usuarioSeguido;

	public Usuario getUsuarioSeguidor() {
		
		return usuarioSeguidor;
	
	}

	public void setUsuarioSeguidor(Usuario usuarioSeguidor) {
		
		this.usuarioSeguidor = usuarioSeguidor;
	
	}

	public Usuario getUsuarioSeguido() {
		
		return usuarioSeguido;
	
	}

	public void setUsuarioSeguido(Usuario usuarioSeguido) {
		
		this.usuarioSeguido = usuarioSeguido;
	
	}

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	
	@Override
	public int hashCode() {
		
		return Objects.hash(usuarioSeguidor, usuarioSeguido);
	
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		UsuarioRelacionamentoPK other = (UsuarioRelacionamentoPK) obj;
		return Objects.equals(usuarioSeguidor, other.usuarioSeguidor)
				&& Objects.equals(usuarioSeguido, other.usuarioSeguido);
	}

}