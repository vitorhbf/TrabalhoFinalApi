package org.serratec.TrabalhoFinalApi.service;

import org.serratec.TrabalhoFinalApi.model.UsuarioRelacionamento;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRelacionamentoService {
    private final UsuarioRelacionamentoRepository usuarioRelacionamentoRepository;

    @Autowired
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

    public void deleteUsuarioRelacionamento(Long seguidorId, Long seguidoId) {
        usuarioRelacionamentoRepository.deleteByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(seguidorId, seguidoId);
    }
}

