package org.serratec.TrabalhoFinalApi.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.TrabalhoFinalApi.model.UsuarioRelacionamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRelacionamentoRepository extends JpaRepository<UsuarioRelacionamento, Long> {

	Optional<UsuarioRelacionamento> findByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(Long seguidorId, Long seguidoId);

	void deleteByIdUsuarioSeguidorIdAndIdUsuarioSeguidoId(Long seguidorId, Long seguidoId);

	List<UsuarioRelacionamento> findByIdUsuarioSeguidoId(Long seguidoId);
}
