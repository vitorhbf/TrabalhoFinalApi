package org.serratec.TrabalhoFinalApi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.dto.UsuarioDTO;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.serratec.TrabalhoFinalApi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public List<UsuarioDTO> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.getAllUsuarios();

		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> {
			UsuarioDTO dto = new UsuarioDTO();
			dto.setId(usuario.getId());
			dto.setNome(usuario.getNome());
			dto.setSobrenome(usuario.getSobrenome());
			dto.setEmail(usuario.getEmail());
			return dto;
		}).collect(Collectors.toList());

		return usuariosDTO;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

		if (usuario.isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(usuario.get().getId());
			usuarioDTO.setNome(usuario.get().getNome());
			usuarioDTO.setSobrenome(usuario.get().getSobrenome());
			usuarioDTO.setEmail(usuario.get().getEmail());
			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario createdUsuario = usuarioService.createUsuario(usuario);

		if (createdUsuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(createdUsuario.getId());
			usuarioDTO.setNome(createdUsuario.getNome());
			usuarioDTO.setSobrenome(createdUsuario.getSobrenome());
			usuarioDTO.setEmail(createdUsuario.getEmail());

			return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> updateUsuario(@Valid @PathVariable Long id, @Valid @RequestBody Usuario usuario) {
	    Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);

	    if (updatedUsuario != null) {
	        UsuarioDTO usuarioDTO = new UsuarioDTO();
	        usuarioDTO.setId(updatedUsuario.getId());
	        usuarioDTO.setNome(updatedUsuario.getNome());
	        usuarioDTO.setSobrenome(updatedUsuario.getSobrenome());
	        usuarioDTO.setEmail(updatedUsuario.getEmail());
	        
	        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.deleteById(id);
		return ResponseEntity.ok("Usuario Id: " + usuarioOpt.get().getNome() + " Excluido com Sucesso !");
	} 

}
