package org.serratec.TrabalhoFinalApi.Security;

import org.serratec.TrabalhoFinalApi.model.Usuario;
import org.serratec.TrabalhoFinalApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario == null) {
			
			return null;
		}
		return new UsuarioDetalhe(usuario);
	}
}
