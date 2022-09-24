package com.br.projetopessoal.api.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.projetopessoal.api.dtos.UserDto;
import com.br.projetopessoal.api.exceptions.ObjectNotFoundException;
import com.br.projetopessoal.api.interfaces.UserInterface;
import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.repositories.UserRepository;

@Service
public class UserService implements UserInterface{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(Integer id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public UserModel create(UserDto userDto) {
        return userRepository.save(mapper.map(userDto, UserModel.class));
    }




    
}
