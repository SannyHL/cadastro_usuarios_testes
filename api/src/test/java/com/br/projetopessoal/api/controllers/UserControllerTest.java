package com.br.projetopessoal.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.br.projetopessoal.api.dtos.UserDto;
import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.services.UserService;

@SpringBootTest
public class UserControllerTest {

    private static final int INDEX = 0;
    private static final String PASSWORD = "123";
    private static final String EMAIL = "fernanda@gmail.com";
    private static final String NAME = "Fernanda";
    private static final Integer ID = 1;

    @InjectMocks
    private UserController userController;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserService service;

    private UserModel user;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void testCreate() {

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void whenFindByIdThenReturnSucess() {
        when(service.findById(anyInt())).thenReturn(user);

        when(mapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void testUpdate() {

    }

    private void startUser(){

        user = new UserModel(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);

    }
}
