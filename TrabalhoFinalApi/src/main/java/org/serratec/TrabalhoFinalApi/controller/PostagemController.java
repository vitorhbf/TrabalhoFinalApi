package org.serratec.TrabalhoFinalApi.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.dto.PostagemDTO;
import org.serratec.TrabalhoFinalApi.excepetion.PostagemNotFoundException;
import org.serratec.TrabalhoFinalApi.excepetion.UsuarioValidation;
import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.model.Usuario;
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
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	public PostagemController(PostagemService postagemService) {
		this.postagemService = postagemService;
	}

	@GetMapping
	@ApiOperation(value = "Lista todas as postagens", notes = "Lista de postagens")
	public List<Postagem> getAllPostagens() {
		return postagemService.getAllPostagens();
	}

	@GetMapping("/postagem/{id}")
	@ApiOperation(value = "Busca uma postagem por ID", notes = "Recupera uma postagem com base no ID fornecido")
	public ResponseEntity<PostagemDTO> getPostagemPorId(@PathVariable Long id) {
		Postagem postagem = postagemService.buscarPostagemPorId(id);

		if (postagem != null) {
			PostagemDTO postagemDTO = postagemService.mapToDTO(postagem);
			return new ResponseEntity<>(postagemDTO, HttpStatus.OK);
		} else {
			throw new PostagemNotFoundException("ID da postagem não é válido: " + postagemService.getPostagemById(id));
		}
	}

	@GetMapping("/usuario/{idUsuario}")
	@ApiOperation(value = "Lista postagens por ID de usuário", notes = "Lista de postagens relacionadas a um usuário específico")
	public ResponseEntity<List<PostagemDTO>> getPostagensByUsuarioId(@PathVariable Long idUsuario)
			throws UsuarioValidation {
		List<PostagemDTO> postagens = postagemService.getPostagensByUsuarioId(idUsuario);

		return new ResponseEntity<>(postagens, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Cria uma nova postagem", notes = "Cria uma nova postagem com os dados fornecidos")
	public ResponseEntity<PostagemDTO> createPostagem(@Valid @RequestBody Postagem postagem) {
		Postagem createdPostagem = postagemService.createPostagem(postagem);

		PostagemDTO postagemDTO = new PostagemDTO();
		postagemDTO.setId(createdPostagem.getId());
		postagemDTO.setConteudoPostagem(createdPostagem.getConteudoPostagem());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(createdPostagem.getDataPostagem());
		postagemDTO.setDataPostagem(dataFormatada);

		postagemDTO.setAutorNome(createdPostagem.getUsuario().getNome());
		postagemDTO.setAutorSobrenome(createdPostagem.getUsuario().getSobrenome());
		postagemDTO.setAutorEmail(createdPostagem.getUsuario().getEmail());

		return new ResponseEntity<>(postagemDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza uma postagem por ID", notes = "Atualiza uma postagem existente com os dados fornecidos")
	public ResponseEntity<PostagemDTO> updatePostagem(@PathVariable Long id,
			@Valid @RequestBody Postagem postagemAtualizada) {
		Postagem postagem = postagemService.updatePostagem(id, postagemAtualizada);

		if (postagem != null) {
			PostagemDTO postagemDTO = mapToDTO(postagem);
			return new ResponseEntity<>(postagemDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private PostagemDTO mapToDTO(Postagem postagem) {
		PostagemDTO postagemDTO = new PostagemDTO();
		postagemDTO.setId(postagem.getId());
		postagemDTO.setConteudoPostagem(postagem.getConteudoPostagem());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(postagem.getDataPostagem());
		postagemDTO.setDataPostagem(dataFormatada);

		Usuario autor = postagem.getUsuario();
		postagemDTO.setAutorNome(autor.getNome());
		postagemDTO.setAutorSobrenome(autor.getSobrenome());
		postagemDTO.setAutorEmail(autor.getEmail());

		return postagemDTO;
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui uma postagem por ID", notes = "Remove uma postagem com base no ID fornecido")
	public ResponseEntity<String> deletarPostagem(@Valid @PathVariable Long id) {
		try {

			Postagem postagem = postagemService.verificarExistenciaPostagem(id);

			postagemService.deletePostagem(id);

			String mensagem = "A postagem com ID " + id + " foi excluída com sucesso.";
			return ResponseEntity.ok(mensagem);
		} catch (PostagemNotFoundException ex) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

}
