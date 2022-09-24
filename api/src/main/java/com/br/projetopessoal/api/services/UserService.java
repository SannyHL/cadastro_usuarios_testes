package com.br.projetopessoal.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.projetopessoal.api.exceptions.ObjectNotFoundException;
import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserModel findById(Integer id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }




    
}
