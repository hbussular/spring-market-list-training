package com.henrique.SchoolApp.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidFields {
    private String field;
    private String error;
}
