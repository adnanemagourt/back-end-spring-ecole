package com.example.back.DTO;

import com.example.back.entities.Classe;
import lombok.*;

@Getter
@EqualsAndHashCode
@Data
public class ClasseDTO {

    private Integer id;

    private String nom;

    public ClasseDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public ClasseDTO(Classe classe) {
        if (classe == null) {return;}
        this.id = classe.getId();
        this.nom = classe.getNom();
    }
}
