package com.henrique.SchoolApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // diz que isso será uma entidade no banco
@Data // Cria todos os getters e setters
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // diz que eh chave primaria e não tem ninguém igual a ela
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private Integer age;
}
