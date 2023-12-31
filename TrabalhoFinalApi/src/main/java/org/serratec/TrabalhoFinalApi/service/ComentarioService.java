package org.serratec.TrabalhoFinalApi.service;

import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.excepetion.ComentarioNotFoundException;
import org.serratec.TrabalhoFinalApi.excepetion.PostagemNotFoundException;
import org.serratec.TrabalhoFinalApi.model.Comentario;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.model.UsuarioRelacionamento;
import org.serratec.TrabalhoFinalApi.repository.ComentarioRepository;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ComentarioService {
	
	@Autowired
	private  ComentarioRepository comentarioRepository;
	
	@Autowired
	private UsuarioRelacionamentoRepository usuarioRelacionamentoRepository;


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
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isPresent()) {
			Comentario comentarioBd = comentarioOpt.get();
			comentarioBd.setDataComentario(comentario.getDataComentario());
			comentarioBd.setConteudoComentario(comentario.getConteudoComentario());

			return comentarioRepository.save(comentarioBd);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comentario com ID " + id + " não encontrada");
	}

	public void deleteComentario(Long id) {
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);

		if (comentarioOpt.isEmpty()) {
			throw new ComentarioNotFoundException("ID do comentário não é válido: " + id);
		}
		comentarioRepository.deleteById(id);
	}

	public List<Comentario> getComentariosByPostagemId(Long idPostagem) {

		return comentarioRepository.findByPostagemId(idPostagem);
	}

	public List<Comentario> getComentariosByPostagemId2(Long idPostagem) {
		List<Comentario> comentarios = comentarioRepository.findByPostagemId(idPostagem);

		if (comentarios.isEmpty()) {
			throw new PostagemNotFoundException("Nenhum comentário encontrado para o ID da postagem: " + idPostagem);
		}

		return comentarios;
	}
	
	public Comentario createComentario(Comentario comentario, Long usuarioLogadoId) {
        Usuario postagemUsuario = comentario.getPostagem().getUsuario();
        if (postagemUsuario != null) {
            if (postagemUsuario.getId() == usuarioLogadoId) {
                
                return comentarioRepository.save(comentario);
            } else {
                
                Optional<UsuarioRelacionamento> relacionamento = usuarioRelacionamentoRepository
                        .findByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(usuarioLogadoId, postagemUsuario.getId());
                if (relacionamento.isPresent()) {
                    return comentarioRepository.save(comentario);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não segue o usuário que criou esta postagem.");
                }
            }
        } else {
            throw new PostagemNotFoundException("A postagem associada ao comentário é nula.");
        }
    }
}