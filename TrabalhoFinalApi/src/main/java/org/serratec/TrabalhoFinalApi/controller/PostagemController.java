package org.serratec.TrabalhoFinalApi.controller;

import org.serratec.TrabalhoFinalApi.model.Postagem;
import org.serratec.TrabalhoFinalApi.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {
    private final PostagemService postagemService;

    @Autowired
    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @GetMapping
    public List<Postagem> getAllPostagens() {
        return postagemService.getAllPostagens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getPostagemById(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemService.getPostagemById(id);
        return postagem.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Postagem> createPostagem(@RequestBody Postagem postagem) {
        Postagem createdPostagem = postagemService.createPostagem(postagem);
        return new ResponseEntity<>(createdPostagem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> updatePostagem(@PathVariable Long id, @RequestBody Postagem postagem) {
        Postagem updatedPostagem = postagemService.updatePostagem(id, postagem);
        return updatedPostagem != null ? new ResponseEntity<>(updatedPostagem, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostagem(@PathVariable Long id) {
        postagemService.deletePostagem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
