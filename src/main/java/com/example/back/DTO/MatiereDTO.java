package com.example.back.DTO;

import com.example.back.entities.Matiere;
import lombok.*;

@Getter
@EqualsAndHashCode
@Data
public class MatiereDTO {
    private Integer id;

    private String nom;

    public MatiereDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public MatiereDTO(Matiere matiere) {
        if (matiere == null) {return;}
        this.id = matiere.getId();
        this.nom = matiere.getNom();
    }

}
