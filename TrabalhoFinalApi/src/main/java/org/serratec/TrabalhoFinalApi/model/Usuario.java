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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	@ApiModelProperty(value = "Identificador unico do usuário")
	private Long id;

	@NotBlank(message = "Preencha o nome do Usuario")
	@Size(max = 60)
	@Column(name = "nome", nullable = false, length = 60)
	@ApiModelProperty(value = "Nome do usuário", required = true)
	private String nome;

	@NotBlank(message = "Preencha o sobrenome do Usuario")
	@Size(max = 60)
	@Column(name = "sobrenome", nullable = false, length = 60)
	@ApiModelProperty(value = "Sobrenome do usuário", required = true)
	private String sobrenome;

	@NotBlank(message = "Preencha o email do Usuario")
	@Size(max = 60)
	@Column(name = "email", nullable = false, length = 60)
	@ApiModelProperty(value = "Endereço de e-mail do usuário", required = true)
	private String email;

	@NotBlank(message = "Preencha a senha do Usuario")
	@Size(max = 60)
	@Column(name = "senha", nullable = false, length = 255)
	@ApiModelProperty(value = "Senha do usuário", required = true)
	private String senha;

	@NotNull(message = "Preencha a data de nascimento do Usuario")
	@Column(name = "data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "Data de nascimento do usuário", required = true)
	private Date dataNascimento;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Postagem> usuarioPostagem = new ArrayList<>();

	@OneToMany(mappedBy = "id.usuarioSeguidor", cascade = CascadeType.REMOVE)
	private List<UsuarioRelacionamento> UsuarioSeguidor = new ArrayList<>();

	@OneToMany(mappedBy = "id.usuarioSeguido", cascade = CascadeType.REMOVE)
	private List<UsuarioRelacionamento> UsuarioSeguido = new ArrayList<>();

	public Usuario() {
	}

	public Usuario(Long id, String nome, String sobrenome, String email, String senha, Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
