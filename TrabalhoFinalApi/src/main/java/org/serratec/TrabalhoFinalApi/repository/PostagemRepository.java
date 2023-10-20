package org.serratec.TrabalhoFinalApi.repository;

import java.util.List;

import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	@Query("SELECT p FROM Postagem p WHERE p.usuario.id = :usuarioId")
	List<Postagem> findByUsuarioId(@Param("usuarioId") Long usuarioId);

}
