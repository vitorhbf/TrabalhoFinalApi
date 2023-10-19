package org.serratec.TrabalhoFinalApi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.model.Postagem;
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

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getPostagemById(@Valid @PathVariable Long id) {
		Optional<Postagem> postagem = postagemService.getPostagemById(id);
		return postagem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Postagem> createPostagem(@Valid @RequestBody Postagem postagem) {
		Postagem createdPostagem = postagemService.createPostagem(postagem);
		return new ResponseEntity<>(createdPostagem, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Postagem> updatePostagem(@Valid @PathVariable Long id,
			@Valid @RequestBody Postagem postagem) {
		Postagem updatedPostagem = postagemService.updatePostagem(id, postagem);
		return updatedPostagem != null ? new ResponseEntity<>(updatedPostagem, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> deletarPostagem(@Valid @PathVariable Long id) {
		boolean resultado = postagemService.deletePostagem(id);
		if(!resultado){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Postagem>(HttpStatus.OK);
	}

}
