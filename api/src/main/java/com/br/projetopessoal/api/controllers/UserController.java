package com.br.projetopessoal.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.projetopessoal.api.dtos.UserDto;
import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     *
     */
    private static final String ID = "/{id}";

    @Autowired
    private UserService service;
    
    @Autowired
    private ModelMapper mapper;
    
    //Listar todos os usuarios:
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok()
        //chamada que retorna uma list de User
        .body(service.findAll()
        // pega cada objeto da list, mapeia os atributos e transformar em user dto
        .stream().map(x -> mapper.map(x, UserDto.class))
        //coletar tudo e transformar em uma lista.
        .collect(Collectors.toList()));
    }
    //Buscar usuario por id;
    @GetMapping(ID)
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDto.class));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest().path(ID).buildAndExpand(service.create(userDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto userDto){
        userDto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(userDto), UserDto.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
