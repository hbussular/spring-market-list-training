package com.henrique.MarketListApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // diz que isso será uma entidade no banco
@Data // Cria todos os getters e setters
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // diz que eh chave primaria e não tem ninguém igual a ela
    private Long id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private Double category;
}
