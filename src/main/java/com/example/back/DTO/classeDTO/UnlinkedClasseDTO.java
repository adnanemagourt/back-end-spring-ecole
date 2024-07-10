package com.example.back.DTO.classeDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Classe;
import lombok.Data;

import java.util.List;

@Data
public class UnlinkedClasseDTO extends ClasseDTO {

    private List<Integer> etudiants;

    private List<Integer> professeurs;

    public UnlinkedClasseDTO(Integer id, String nom, List<Integer> etudiants, List<Integer> professeurs) {
        super(id, nom);
        this.etudiants = etudiants;
        this.professeurs = professeurs;
    }

    public UnlinkedClasseDTO(Classe classe) {
        super(classe);
        if (classe == null) {return;}
        this.etudiants = DTOListMapper.mapEtudiantIds(classe.etudiants);
        this.professeurs = DTOListMapper.mapProfesseurIds(classe.professeurs);
    }
}
