package com.example.back.DTO.matiereDTO;

import com.example.back.entities.Matiere;
import lombok.Data;

@Data
public class MatiereDTO {
    private Integer id;

    private String nom;

    public MatiereDTO() {
    }

    public MatiereDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public MatiereDTO(Matiere matiere) {
        if (matiere == null) {
            return;
        }
        this.id = matiere.getId();
        this.nom = matiere.getNom();
    }

}
