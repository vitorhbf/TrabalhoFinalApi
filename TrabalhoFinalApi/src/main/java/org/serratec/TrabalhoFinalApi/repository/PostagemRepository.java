package org.serratec.TrabalhoFinalApi.repository;


import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}
