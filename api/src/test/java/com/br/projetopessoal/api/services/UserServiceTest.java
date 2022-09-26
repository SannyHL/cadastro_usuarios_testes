package com.br.projetopessoal.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import com.br.projetopessoal.api.dtos.UserDto;
import com.br.projetopessoal.api.exceptions.DataIntegratyViolationException;
import com.br.projetopessoal.api.exceptions.ObjectNotFoundException;
import com.br.projetopessoal.api.model.UserModel;
import com.br.projetopessoal.api.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

    
    private static final int INDEX = 0;
    private static final String PASSWORD = "123";
    private static final String EMAIL = "fernanda@gmail.com";
    private static final String NAME = "Fernanda";
    private static final Integer ID = 1;

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private UserModel user = new UserModel();
    private UserDto userDto = new UserDto();
    private Optional<UserModel> userOptional;
    

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);

        UserModel response = service.create(userDto);

        assertNotNull(response);
        assertEquals(UserModel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }
    
    @Test
    void whenCreateThenReturnDataIntegratyViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2);
            service.create(userDto);
        } catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("Esse email já foi ultlizado, favor informar outro!", e.getMessage());
        }

    }

    @Test
    void whenDeleteWithSucess() {
        when(userRepository.findById(anyInt())).thenReturn(userOptional);
        doNothing().when(userRepository).deleteById(anyInt());
        service.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());

    }

    @Test
    void deleteWithObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        try {
            service.delete(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Objeto não encontrado", e.getMessage());
            
        }
    }

    @Test
    void whenFindAllReturnAnLisOftUser() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserModel> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(UserModel.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(userOptional);

        UserModel response = service.findById(ID);

        assertNotNull(response);
        assertEquals(UserModel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            service.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Objeto não encontrado", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);

        UserModel response = service.update(userDto);

        assertNotNull(response);
        assertEquals(UserModel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegratyViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2);
            service.create(userDto);
        } catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("Esse email já foi ultlizado, favor informar outro!", e.getMessage());
        }

    }

    private void startUser(){

        user = new UserModel(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new UserModel(ID, NAME, EMAIL, PASSWORD));

    }
}
