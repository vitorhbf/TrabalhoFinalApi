package org.serratec.TrabalhoFinalApi.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_relacionamento")
@Embeddable
public class Relacionamento  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_seguidor")
	private Usuario usuarioSeguidor;
	
	@ManyToOne
	@JoinColumn(name = "id_seguido")
	private Usuario usuarioSeguido;

}
