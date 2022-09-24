package com.br.projetopessoal.api.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {
    
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
