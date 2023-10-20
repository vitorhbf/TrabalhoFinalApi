package org.serratec.TrabalhoFinalApi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.dto.PostagemDTO;
import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.service.PostagemService;
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
@RequestMapping("/postagens")
public class PostagemController {
	private final PostagemService postagemService;

	public PostagemController(PostagemService postagemService) {
		this.postagemService = postagemService;
	}

	@GetMapping
	public List<Postagem> getAllPostagens() {
		return postagemService.getAllPostagens();
	}

	@GetMapping("{idUsuario}")
	public ResponseEntity<List<PostagemDTO>> getPostagensByUsuarioId(@Valid @PathVariable Long idUsuario) {
		List<Postagem> postagens = postagemService.getPostagensByUsuarioId(idUsuario);
		List<PostagemDTO> postagemDTOs = new ArrayList<>();

		for (Postagem postagem : postagens) {
			PostagemDTO postagemDTO = new PostagemDTO();
			postagemDTO.setId(postagem.getId());
			postagemDTO.setConteudoPostagem(postagem.getConteudoPostagem());

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dataFormatada = dateFormat.format(postagem.getDataPostagem());
			postagemDTO.setDataPostagem(dataFormatada);

			postagemDTO.setAutorNome(postagem.getUsuario().getNome());
			postagemDTO.setAutorSobrenome(postagem.getUsuario().getSobrenome());
			postagemDTO.setAutorEmail(postagem.getUsuario().getEmail());

			postagemDTOs.add(postagemDTO);
		}

		return new ResponseEntity<>(postagemDTOs, HttpStatus.OK);
	}

	@PostMapping
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
	public ResponseEntity<String> deletarPostagem(@Valid @PathVariable Long id) {
	    boolean resultado = postagemService.deletePostagem(id);
	    if (!resultado) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    String mensagem = "A postagem com ID " + id + " foi excluída com sucesso.";
	    return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}


}
