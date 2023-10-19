package org.serratec.TrabalhoFinalApi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.TrabalhoFinalApi.model.Comentario;
import org.serratec.TrabalhoFinalApi.repository.ComentarioRepository;
import org.serratec.TrabalhoFinalApi.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    private final ComentarioService comentarioService;

    @Autowired
    private ComentarioRepository comentarioRepository;
    
    
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioService.getAllComentarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@Valid @PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        return comentario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(@Valid @RequestBody Comentario comentario) {
        Comentario createdComentario = comentarioService.createComentario(comentario);
        return new ResponseEntity<>(createdComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario( @Valid @PathVariable Long id, @Valid @RequestBody Comentario comentario) {
        Comentario updatedComentario = comentarioService.updateComentario(id, comentario);
        return updatedComentario != null ? new ResponseEntity<>(updatedComentario, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<String> deletarPostagem(@Valid @PathVariable Long id) {
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		comentarioRepository.deleteById(id);
		return ResponseEntity.ok("comentario Id: " + comentarioOpt.get().getId()+ " Excluido com Sucesso !");
	}
    
    /*public ResponseEntity<Void> remover(@PathVariable Long id){
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if(comentarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		comentarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}*/
}
