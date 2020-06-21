package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.api.model.ComentarioInput;
import com.algaworks.osworks.api.model.ComentarioModel;
import com.algaworks.osworks.domain.service.CrudOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ordens-servico/{comentarioServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private CrudOrderService crudOrderService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long comentarioServicoId,
                                     @Valid @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = crudOrderService.adicionarComentario(comentarioServicoId, comentarioInput.getDescricao());
        return this.toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }
}
