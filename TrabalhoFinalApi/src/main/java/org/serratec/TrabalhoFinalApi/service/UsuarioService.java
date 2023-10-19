package org.serratec.TrabalhoFinalApi.service;

import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null; // Trate o cenário em que o usuário com o ID especificado não existe.
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
