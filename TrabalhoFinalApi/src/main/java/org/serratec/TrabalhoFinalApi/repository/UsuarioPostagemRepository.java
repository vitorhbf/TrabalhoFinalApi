package org.serratec.TrabalhoFinalApi.repository;


import org.serratec.TrabalhoFinalApi.model.UsuarioPostagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPostagemRepository extends JpaRepository<UsuarioPostagem, Long>{

}
