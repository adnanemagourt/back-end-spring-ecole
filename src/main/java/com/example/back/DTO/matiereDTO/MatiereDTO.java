package com.example.back.DTO.matiereDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.professeurDTO.ProfesseurDTO;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.entities.Matiere;
import lombok.*;

import java.util.List;

@Data
public class MatiereDTO {
    private Integer id;

    private String nom;

    public MatiereDTO() {}

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
