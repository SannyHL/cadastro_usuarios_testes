package com.br.projetopessoal.api.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    
    @GetMapping("/")
    public ResponseEntity<List<UserModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable(value = "id") Integer id){
        
        return ResponseEntity.ok().body(service.findById(id));
    }
    
}
