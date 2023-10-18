package org.serratec.TrabalhoFinalApi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.serratec.TrabalhoFinalApi.dto.UsuarioDTO;
import org.serratec.TrabalhoFinalApi.dto.UsuarioInserirDTO;
import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.model.UsuarioPostagem;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostagemService postagemService;
	
	
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		/*
		 * List<UsuarioDTO> usuariosDTO = new ArrayList<>(); for (Usuario usuario:
		 * usuarios) { usuariosDTO.add(new UsuarioDTO(usuario)); }
		 */
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario))
				.collect(Collectors.toList());
		return usuariosDTO;
	}

	public UsuarioDTO findById(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOpt.get());
		return usuarioDTO;
	}

	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		if (!usuarioInserirDTO.getSenha().equalsIgnoreCase(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha devem ser iguais");
		}

		Usuario usuarioEmailExistente = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (usuarioEmailExistente != null) {
			throw new EmailException("Email já cadastrado.");
		}

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));

		Set<UsuarioPostagem> usuarioPerfis = new HashSet<>();
		for (Postagem postagem : usuarioInserirDTO.getPerfis()) {
			postagem = postagemService.buscar(postagem.getId());
			UsuarioPostagem usuarioPostagem = new UsuarioPostagem(usuario, postagem);
			usuarioPerfis.add(usuarioPostagem);
		}

		usuario.setUsuarioPostagem(usuarioPostagem);

		usuario = usuarioRepository.save(usuario);
		

		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}

}
