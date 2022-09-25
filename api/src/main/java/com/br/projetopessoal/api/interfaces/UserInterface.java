package com.br.projetopessoal.api.interfaces;

import java.util.List;

import com.br.projetopessoal.api.dtos.UserDto;
import com.br.projetopessoal.api.model.UserModel;


public interface UserInterface {

    UserModel findById(Integer id);
    List<UserModel> findAll();
    UserModel create(UserDto userDto);
    UserModel update(UserDto userDto);
    void delete(Integer id);
}
