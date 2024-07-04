package com.example.back.DTO;

import com.example.back.entities.Classe;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
@Data
public class ClasseDTO {

    private Integer id;

    private String nom;

    private List<EtudiantDTO> etudiants;

    private List<ProfesseurDTO> professeurs;

    public ClasseDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public ClasseDTO(Classe classe) {
        if (classe == null) {return;}
        this.id = classe.getId();
        this.nom = classe.getNom();
        this.etudiants = DTOListMapper.mapEtudiant(classe.etudiants);
        this.professeurs = DTOListMapper.mapProfesseur(classe.professeurs);
    }
}
