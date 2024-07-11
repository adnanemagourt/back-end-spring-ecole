package com.example.back.DTO.matiereDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Matiere;
import lombok.Data;

import java.util.List;

@Data
public class UnlinkedMatiereDTO extends MatiereDTO {

    private List<Integer> etudiants;

    private List<Integer> professeurs;

    public UnlinkedMatiereDTO(Integer id, String nom, List<Integer> etudiants, List<Integer> professeurs) {
        super(id, nom);
        this.etudiants = etudiants;
        this.professeurs = professeurs;
    }

    public UnlinkedMatiereDTO(Matiere matiere) {
        super(matiere);
        if (matiere == null) {
            return;
        }
        this.etudiants = DTOListMapper.mapEtudiantIds(matiere.etudiants);
        this.professeurs = DTOListMapper.mapProfesseurIds(matiere.professeurs);
    }

}
