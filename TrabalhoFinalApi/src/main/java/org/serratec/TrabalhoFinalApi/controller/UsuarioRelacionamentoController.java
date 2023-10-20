package org.serratec.TrabalhoFinalApi.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.model.UsuarioRelacionamento;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.serratec.TrabalhoFinalApi.service.UsuarioRelacionamentoService;
import org.serratec.TrabalhoFinalApi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relacionamentos")
public class UsuarioRelacionamentoController {
	private final UsuarioRelacionamentoService usuarioRelacionamentoService;
	private final UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioRelacionamentoController(UsuarioRelacionamentoService usuarioRelacionamentoService,
			UsuarioService usuarioService) {
		this.usuarioRelacionamentoService = usuarioRelacionamentoService;
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public List<UsuarioRelacionamento> getAllUsuarioRelacionamentos() {
		return usuarioRelacionamentoService.getAllUsuarioRelacionamentos();
	}

	@GetMapping("/{seguidorId}/{seguidoId}")
	public ResponseEntity<UsuarioRelacionamento> getUsuarioRelacionamentoById(@Valid @PathVariable Long seguidorId,
			@PathVariable Long seguidoId) {
		Optional<UsuarioRelacionamento> usuarioRelacionamento = usuarioRelacionamentoService
				.getUsuarioRelacionamentoById(seguidorId, seguidoId);
		return usuarioRelacionamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/seguir")
	public ResponseEntity<UsuarioRelacionamento> seguirUsuario(
			@Valid @RequestBody UsuarioRelacionamento usuarioRelacionamento) {
		if (usuarioRelacionamento == null || usuarioRelacionamento.getId() == null
				|| usuarioRelacionamento.getId().getUsuarioSeguidor() == null
				|| usuarioRelacionamento.getId().getUsuarioSeguido() == null) {
			return ResponseEntity.badRequest().build();
		}

		Long seguidorId = usuarioRelacionamento.getId().getUsuarioSeguidor().getId();
		Long seguidoId = usuarioRelacionamento.getId().getUsuarioSeguido().getId();

		Usuario seguidor = usuarioService.getUsuarioById(seguidorId)
				.orElseThrow(() -> new RuntimeException("Seguidor não encontrado"));
		Usuario seguido = usuarioService.getUsuarioById(seguidoId)
				.orElseThrow(() -> new RuntimeException("Seguido não encontrado"));

		UsuarioRelacionamento novaRelacao = new UsuarioRelacionamento(LocalDate.now(), seguidor, seguido);
		UsuarioRelacionamento createdUsuarioRelacionamento = usuarioRelacionamentoService
				.createUsuarioRelacionamento(novaRelacao);

		return new ResponseEntity<>(createdUsuarioRelacionamento, HttpStatus.CREATED);
	}

	@DeleteMapping("/{seguidorId}/{seguidoId}")
	public ResponseEntity<Void> deleteUsuarioRelacionamento(@PathVariable Long seguidorId,
			@PathVariable Long seguidoId) {
		usuarioRelacionamentoService.deleteUsuarioRelacionamento(seguidorId, seguidoId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
