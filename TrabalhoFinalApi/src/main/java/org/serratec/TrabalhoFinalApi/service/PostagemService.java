package org.serratec.TrabalhoFinalApi.service;

import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.repository.PostagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostagemService {
	private final PostagemRepository postagemRepository;

	public PostagemService(PostagemRepository postagemRepository) {
		this.postagemRepository = postagemRepository;
	}

	public List<Postagem> getAllPostagens() {
		return postagemRepository.findAll();
	}

	public Optional<Postagem> getPostagemById(Long id) {
		if (postagemRepository.existsById(id)) {
			return postagemRepository.findById(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Postagem com ID " + id + " não encontrada");
	}

	public Postagem createPostagem(Postagem postagem) {
		return postagemRepository.save(postagem);
	}

	public Postagem updatePostagem(Long id, Postagem postagem) {
		if (postagemRepository.existsById(id)) {
			postagem.setId(id);
			return postagemRepository.save(postagem);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Postagem com ID " + id + " não encontrada"); 
		}
	}

	public boolean deletePostagem(Long id) {

		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {
			return false;
		}

		postagemRepository.delete(postagemOpt.get());
		return true;
	}

}
