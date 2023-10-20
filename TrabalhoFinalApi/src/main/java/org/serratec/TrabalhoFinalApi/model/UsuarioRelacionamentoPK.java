package org.serratec.TrabalhoFinalApi.model;

import java.io.Serializable;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class UsuarioRelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull(message = "O campo 'usuarioSeguidor' é obrigatório.")
	@JoinColumn(name = "id_seguidor", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Valid
	private Usuario usuarioSeguidor;

	@ManyToOne
	@NotNull(message = "O campo 'usuarioSeguido' é obrigatório.")
	@JoinColumn(name = "id_seguido", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Valid
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