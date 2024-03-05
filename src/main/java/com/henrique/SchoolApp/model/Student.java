package com.henrique.SchoolApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity // diz que isso será uma entidade no banco
@Data // Cria todos os getters e setters
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // diz que eh chave primaria e não tem ninguém igual a ela
    private Long id;

    @Column @NotNull @NotEmpty @Length(max = 40)
    private String username;

    @Column
    private String password;

    @Column @NotNull @NotEmpty @Email
    private String email;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Integer age;

    @Column @NotNull @NotEmpty @CPF
    private String cpf;
}
