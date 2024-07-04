package com.example.back.DTO;

import com.example.back.entities.Matiere;
import lombok.*;

import java.util.List;

@Getter
@EqualsAndHashCode
@Data
public class MatiereDTO {
    private Integer id;

    private String nom;

    private List<EtudiantDTO> etudiants;

    private List<ProfesseurDTO> professeurs;

    public MatiereDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public MatiereDTO(Matiere matiere) {
        if (matiere == null) {return;}
        this.id = matiere.getId();
        this.nom = matiere.getNom();
        this.etudiants = DTOListMapper.mapEtudiant(matiere.etudiants);
        this.professeurs = DTOListMapper.mapProfesseur(matiere.professeurs);
    }

}
