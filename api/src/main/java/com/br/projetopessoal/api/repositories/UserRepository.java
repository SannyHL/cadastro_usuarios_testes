package com.br.projetopessoal.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projetopessoal.api.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{
    
}
