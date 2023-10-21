package org.serratec.TrabalhoFinalApi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.dto.PostagemDTO;
import org.serratec.TrabalhoFinalApi.excepetion.PostagemNotFoundException;
import org.serratec.TrabalhoFinalApi.excepetion.UsuarioValidation;
import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.repository.PostagemRepository;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostagemService {

	private PostagemRepository postagemRepository;

	private UsuarioRepository usuarioRepository;

	public PostagemService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository) {
		this.postagemRepository = postagemRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public List<Postagem> getAllPostagens() {
		return postagemRepository.findAll();
	}

	public Optional<Postagem> getPostagemById(Long id) {
		if (postagemRepository.existsById(id)) {

			return postagemRepository.findById(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem com ID " + id + " não encontrada");
	}

	public Postagem createPostagem(Postagem postagem) {
		return postagemRepository.save(postagem);
	}

	public Postagem updatePostagem(Long id, Postagem postagem) {
		if (postagemRepository.existsById(id)) {
			postagem.setId(id);

			return postagemRepository.save(postagem);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem com ID " + id + " não encontrada");
		}
	}

	public boolean deletePostagem(Long id) {

		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {

			return false;
		}
		postagemRepository.deleteById(postagemOpt.get().getId());

		return true;
	}

	public List<PostagemDTO> getPostagensByUsuarioId(Long idUsuario) throws UsuarioValidation {

		Optional<Usuario> usuarioOTP = usuarioRepository.findById(idUsuario);
		if (usuarioOTP.isEmpty()) {
			throw new UsuarioValidation("Usuario não Encontrado com o Id: " + idUsuario);
		}
		List<Postagem> postagens = postagemRepository.findByUsuarioId(idUsuario);
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
		return postagemDTOs;
	}

	public Postagem buscarPostagemPorId(Long postagemId) {
		return postagemRepository.findByPostagemId(postagemId);
	}

	public PostagemDTO mapToDTO(Postagem postagem) {
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

	public Postagem verificarExistenciaPostagem(Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {
			throw new PostagemNotFoundException("Não existe postagem com o ID: " + id);
		}
		return postagemOpt.get();
	}
}
