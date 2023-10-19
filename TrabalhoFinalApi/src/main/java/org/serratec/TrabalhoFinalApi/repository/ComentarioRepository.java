package org.serratec.TrabalhoFinalApi.repository;

import org.serratec.TrabalhoFinalApi.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
 
}
