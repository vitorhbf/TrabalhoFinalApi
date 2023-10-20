package org.serratec.TrabalhoFinalApi.service;

import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
        
    }

    public Optional<Usuario> getUsuarioById(Long id) { 
        if (usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario com ID " + id + " não encontrada");
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario com ID " + id + " não encontrada");
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    
}
