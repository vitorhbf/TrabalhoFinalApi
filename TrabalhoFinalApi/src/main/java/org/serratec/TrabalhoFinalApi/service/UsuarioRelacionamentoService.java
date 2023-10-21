package org.serratec.TrabalhoFinalApi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.TrabalhoFinalApi.excepetion.UsuarioNaoEncontradoException;
import org.serratec.TrabalhoFinalApi.model.UsuarioRelacionamento;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRelacionamentoRepository;

import org.springframework.stereotype.Service;

@Service
public class UsuarioRelacionamentoService {
	private final UsuarioRelacionamentoRepository usuarioRelacionamentoRepository;

	public UsuarioRelacionamentoService(UsuarioRelacionamentoRepository usuarioRelacionamentoRepository) {
		this.usuarioRelacionamentoRepository = usuarioRelacionamentoRepository;
	}

	public List<UsuarioRelacionamento> getAllUsuarioRelacionamentos() {
		return usuarioRelacionamentoRepository.findAll();
	}

	public Optional<UsuarioRelacionamento> getUsuarioRelacionamentoById(Long seguidorId, Long seguidoId) {
		return usuarioRelacionamentoRepository.findByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(seguidorId, seguidoId);
	}

	public UsuarioRelacionamento createUsuarioRelacionamento(UsuarioRelacionamento usuarioRelacionamento) {
		return usuarioRelacionamentoRepository.save(usuarioRelacionamento);
	}

	@Transactional
	public void deleteUsuarioRelacionamento(Long seguidorId, Long seguidoId) {
		usuarioRelacionamentoRepository.deleteByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(seguidorId, seguidoId);
	}

	public List<UsuarioRelacionamento> getUsuarioSeguidores(Long seguidoId) {
		List<UsuarioRelacionamento> seguidores = usuarioRelacionamentoRepository.findByIdUsuarioSeguidoId(seguidoId);

		if (seguidores.isEmpty()) {
			throw new UsuarioNaoEncontradoException("Nenhum seguidor encontrado para o usuário com ID: " + seguidoId);
		}

		return seguidores;
	}
}
