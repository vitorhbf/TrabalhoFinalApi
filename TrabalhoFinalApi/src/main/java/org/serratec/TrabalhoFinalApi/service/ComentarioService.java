package org.serratec.TrabalhoFinalApi.service;

import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.model.Comentario;
import org.serratec.TrabalhoFinalApi.repository.ComentarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ComentarioService {
	private final ComentarioRepository comentarioRepository;

	public ComentarioService(ComentarioRepository comentarioRepository) {
		this.comentarioRepository = comentarioRepository;
	}

	public List<Comentario> getAllComentarios() {
		return comentarioRepository.findAll();
	}

	public Optional<Comentario> getComentarioById(Long id) {

		if (comentarioRepository.existsById(id)) {
			return comentarioRepository.findById(id);

		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comentario com ID " + id + " não encontrada");
	}

	public Comentario createComentario(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}

	public Comentario updateComentario(Long id, Comentario comentario) {
		if (comentarioRepository.existsById(id)) {
			comentario.setId(id);
			return comentarioRepository.save(comentario);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comentario com ID " + id + " não encontrada");
	}

	public void deleteComentario(Long id) {
		comentarioRepository.deleteById(id);
	}

	public List<Comentario> getComentariosByPostagemId(Long idPostagem) {
		return comentarioRepository.findByPostagemId(idPostagem);
	}
}