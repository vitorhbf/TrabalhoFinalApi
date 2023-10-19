package org.serratec.TrabalhoFinalApi.service;

import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {
    private final PostagemRepository postagemRepository;

    @Autowired
    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public List<Postagem> getAllPostagens() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> getPostagemById(Long id) {
        return postagemRepository.findById(id);
    }

    public Postagem createPostagem(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    public Postagem updatePostagem(Long id, Postagem postagem) {
        if (postagemRepository.existsById(id)) {
            postagem.setId(id);
            return postagemRepository.save(postagem);
        } else {
            return null; // Ou você pode lançar uma exceção ou lidar de outra forma
        }
    }

    public void deletePostagem(Long id) {
        postagemRepository.deleteById(id);
    }
}
