package com.br.projetopessoal.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDataBase(){
        var user1 = new UserModel(null, "Fernanda", "fernanda@gmail.com", "123");
        var user2 = new UserModel(null, "Pedro", "pedro@gmail.com", "123");

        userRepository.saveAll(List.of(user1, user2));
    }

    
}
