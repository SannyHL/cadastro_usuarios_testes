package com.br.projetopessoal.api.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    @Email
    private String email;
    private String password;



}
