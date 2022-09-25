package com.br.projetopessoal.api.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.projetopessoal.api.dtos.UserDto;
import com.br.projetopessoal.api.exceptions.DataIntegratyViolationException;
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
        findByEmail(userDto);
        return userRepository.save(mapper.map(userDto, UserModel.class));
    }

    
    private void findByEmail(UserDto userDto){
        Optional<UserModel> userOptional = userRepository.findByEmail(userDto.getEmail());
        // Se já tem o email no banco de dados e se o email cadastrado já tiver no banco de dados e for diferente do email do id passado.
        if(userOptional.isPresent() && !userOptional.get().getId().equals(userDto.getId())){
            throw new DataIntegratyViolationException("Esse email já foi ultlizado, favor informar outro!");
        }
    }

    @Override
    public UserModel update(UserDto userDto) {
        findByEmail(userDto);
        return userRepository.save(mapper.map(userDto, UserModel.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);;
        
    }
    


    
}
