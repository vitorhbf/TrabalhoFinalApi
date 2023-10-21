package org.serratec.TrabalhoFinalApi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.dto.ComentarioDTO;
import org.serratec.TrabalhoFinalApi.dto.PostagemDTO;
import org.serratec.TrabalhoFinalApi.dto.UsuarioDTO;
import org.serratec.TrabalhoFinalApi.excepetion.ComentarioNotFoundException;
import org.serratec.TrabalhoFinalApi.excepetion.PostagemNotFoundException;
import org.serratec.TrabalhoFinalApi.model.Comentario;
import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.service.ComentarioService;
import org.serratec.TrabalhoFinalApi.service.PostagemService;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
	private final ComentarioService comentarioService;

	public ComentarioController(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os comentários", notes = "Lista de comentários")
	public List<Comentario> getAllComentarios() {
		return comentarioService.getAllComentarios();
	}

	@GetMapping("/postagem/{idPostagem}")
	@ApiOperation(value = "Lista comentários por ID de postagem", notes = "Lista de comentários relacionados a uma postagem específica")
	public ResponseEntity<?> getComentariosByPostagemId(@Valid @PathVariable Long idPostagem) {
		try {
			List<Comentario> comentarios = comentarioService.getComentariosByPostagemId2(idPostagem);
			List<ComentarioDTO> comentarioDTOs = new ArrayList<>();

			for (Comentario comentario : comentarios) {
				ComentarioDTO comentarioDTO = new ComentarioDTO();
				comentarioDTO.setId(comentario.getId());
				comentarioDTO.setConteudoComentario(comentario.getConteudoComentario());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatada = dateFormat.format(comentario.getDataComentario());
				comentarioDTO.setDataComentario(dataFormatada);
				Usuario usuario = comentario.getPostagem().getUsuario();
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setId(usuario.getId());
				usuarioDTO.setNome(usuario.getNome());
				usuarioDTO.setSobrenome(usuario.getSobrenome());
				usuarioDTO.setEmail(usuario.getEmail());
				comentarioDTO.setUsuario(usuarioDTO);
				comentarioDTOs.add(comentarioDTO);
			}
			return new ResponseEntity<>(comentarioDTOs, HttpStatus.OK);
		} catch (PostagemNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PostMapping
	@ApiOperation(value = "Cria um novo comentário", notes = "Cria um novo comentário com os dados fornecidos")
	public ResponseEntity<?> createComentario(@Valid @RequestBody Comentario comentario,
			@Autowired PostagemService postagemService) {
		if (comentario.getPostagem().getId() != null) {
			Comentario createdComentario = comentarioService.createComentario(comentario);
			return new ResponseEntity<>(createdComentario, HttpStatus.CREATED);
		} else {
			throw new PostagemNotFoundException("A postagem associada ao comentário é nula.");
		}
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um comentário por ID", notes = "Atualiza um comentário existente com os dados fornecidos")
	public ResponseEntity<?> updateComentario(@Valid @PathVariable Long id, @Valid @RequestBody Comentario comentario) {
		Comentario updatedComentario = comentarioService.updateComentario(id, comentario);

		if (updatedComentario != null) {
			ComentarioDTO comentarioDTO = mapToDTO(updatedComentario);
			return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
		} else {
			String errorMessage = "A postagem associada ao comentário não foi encontrada.";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}

	private ComentarioDTO mapToDTO(Comentario comentario) {
		ComentarioDTO comentarioDTO = new ComentarioDTO();
		comentarioDTO.setId(comentario.getId());
		comentarioDTO.setConteudoComentario(comentario.getConteudoComentario());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(comentario.getDataComentario());
		comentarioDTO.setDataComentario(dataFormatada);
		Postagem postagem = comentario.getPostagem();
		PostagemDTO postagemDTO = mapPostagemToDTO(postagem);
		comentarioDTO.setPostagem(postagemDTO);

		return comentarioDTO;
	}

	private PostagemDTO mapPostagemToDTO(Postagem postagem) {
		PostagemDTO postagemDTO = new PostagemDTO();
		postagemDTO.setId(postagem.getId());
		postagemDTO.setConteudoPostagem(postagem.getConteudoPostagem());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(postagem.getDataPostagem());
		postagemDTO.setDataPostagem(dataFormatada);

		return postagemDTO;
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um comentário por ID", notes = "Remove um comentário com base no ID fornecido")
	public ResponseEntity<String> deletarComentario(@Valid @PathVariable Long id) {
		try {
			comentarioService.deleteComentario(id);
			return ResponseEntity.ok("Comentário com ID " + id + " excluído com sucesso.");
		} catch (ComentarioNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
}
