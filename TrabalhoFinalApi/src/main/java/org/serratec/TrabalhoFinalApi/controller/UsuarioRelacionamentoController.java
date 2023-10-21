package org.serratec.TrabalhoFinalApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.dto.UsuarioRelacionamentoDTO;
import org.serratec.TrabalhoFinalApi.dto.UsuarioSeguidoDTO;
import org.serratec.TrabalhoFinalApi.dto.UsuarioSeguidorDTO;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.model.UsuarioRelacionamento;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRelacionamentoRepository;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/relacionamentos")
public class UsuarioRelacionamentoController {

	@Autowired
	UsuarioRelacionamentoService usuarioRelacionamentoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRelacionamentoRepository usuarioRelacionamentoRepository;

	public UsuarioRelacionamentoController(UsuarioRelacionamentoService usuarioRelacionamentoService,
			UsuarioService usuarioService) {
		this.usuarioRelacionamentoService = usuarioRelacionamentoService;
		this.usuarioService = usuarioService;
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os relacionamentos de usuários", notes = "Lista de relacionamentos entre usuários")
	public List<UsuarioRelacionamento> getAllUsuarioRelacionamentos() {
		return usuarioRelacionamentoService.getAllUsuarioRelacionamentos();
	}

	@GetMapping("/seguidores/{seguidoId}")
	@ApiOperation(value = "Lista seguidores de um usuário", notes = "Lista de usuários que seguem o usuário especificado")
	public ResponseEntity<List<UsuarioSeguidorDTO>> getSeguidores(@Valid @PathVariable Long seguidoId) {
		List<UsuarioRelacionamento> seguidores = usuarioRelacionamentoService.getUsuarioSeguidores(seguidoId);
		List<UsuarioSeguidorDTO> seguidoresDTO = new ArrayList<>();

		for (UsuarioRelacionamento seguidor : seguidores) {
			UsuarioSeguidorDTO seguidorDTO = new UsuarioSeguidorDTO();
			seguidorDTO.setId(seguidor.getId().getUsuarioSeguidor().getId());
			seguidorDTO.setNome(seguidor.getId().getUsuarioSeguidor().getNome());
			seguidorDTO.setSobrenome(seguidor.getId().getUsuarioSeguidor().getSobrenome());

			String email = seguidor.getId().getUsuarioSeguidor().getEmail();
			seguidorDTO.setEmail(email);

			seguidoresDTO.add(seguidorDTO);
		}

		if (!seguidoresDTO.isEmpty()) {
			return new ResponseEntity<>(seguidoresDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/seguir")
	@ApiOperation(value = "Seguir um usuário", notes = "Cria um relacionamento de seguir entre dois usuários")
	public ResponseEntity<UsuarioRelacionamentoDTO> seguirUsuario(
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

		UsuarioSeguidorDTO seguidorDTO = new UsuarioSeguidorDTO();
		seguidorDTO.setId(seguidor.getId());
		seguidorDTO.setNome(seguidor.getNome());
		seguidorDTO.setSobrenome(seguidor.getSobrenome());
		seguidorDTO.setEmail(seguidor.getEmail());

		UsuarioSeguidoDTO seguidoDTO = new UsuarioSeguidoDTO();
		seguidoDTO.setId(seguido.getId());
		seguidoDTO.setNome(seguido.getNome());
		seguidoDTO.setSobrenome(seguido.getSobrenome());
		seguidoDTO.setEmail(seguido.getEmail());

		UsuarioRelacionamentoDTO usuarioRelacionamentoDTO = new UsuarioRelacionamentoDTO();
		usuarioRelacionamentoDTO.setSeguidor(seguidorDTO);
		usuarioRelacionamentoDTO.setSeguido(seguidoDTO);
		
		
		usuarioRelacionamentoRepository.save(usuarioRelacionamento);
		

		return new ResponseEntity<>(usuarioRelacionamentoDTO, HttpStatus.CREATED);
	}

	@DeleteMapping("/deixar-de-seguir/{seguidorId}/{seguidoId}")
	@ApiOperation(value = "Deixar de seguir um usuário", notes = "Remove o relacionamento de seguir entre dois usuários")
	public ResponseEntity<String> deixarDeSeguir(@Valid @PathVariable Long seguidorId,
			@Valid @PathVariable Long seguidoId) {
		Optional<UsuarioRelacionamento> relacionamentoExistente = usuarioRelacionamentoService
				.getUsuarioRelacionamentoById(seguidorId, seguidoId);

		if (relacionamentoExistente.isPresent()) {
			usuarioRelacionamentoService.deleteUsuarioRelacionamento(seguidorId, seguidoId);
			String mensagem = "Usuário de ID " + seguidorId + " deixou de seguir o usuário de ID " + seguidoId;
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Relação de seguidor não encontrada", HttpStatus.NOT_FOUND);
		}
	}

}
