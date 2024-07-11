package com.example.back.DTO.classeDTO;

import com.example.back.entities.Classe;
import lombok.Data;

@Data
public class ClasseDTO {

    private Integer id;

    private String nom;

    public ClasseDTO() {
    }

    public ClasseDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public ClasseDTO(Classe classe) {
        if (classe != null) {
            this.id = classe.getId();
            this.nom = classe.getNom();
        }
    }
}
