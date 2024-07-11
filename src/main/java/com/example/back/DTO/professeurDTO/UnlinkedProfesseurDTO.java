package com.example.back.DTO.professeurDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Matiere;
import com.example.back.entities.Professeur;
import lombok.Data;

import java.util.List;

@Data
public class UnlinkedProfesseurDTO extends ProfesseurDTO {

    public Integer matiere_id;

    private List<Integer> classes;

    public UnlinkedProfesseurDTO(Integer id, String nom, String prenom, String email, String motDePasse, String adresse, String telephone, Integer matiere_id, List<Integer> classes) {
        super(id, nom, prenom, email, motDePasse, adresse, telephone);
        this.matiere_id = matiere_id;
        this.classes = classes;
    }

    public UnlinkedProfesseurDTO(Professeur professeur) {
        super(professeur);
        if (professeur == null) {
            return;
        }
        Matiere matiere = professeur.getMatiere();
        this.matiere_id = matiere == null ? null : matiere.getId();
        this.classes = DTOListMapper.mapClasseIds(professeur.getClasses());
    }

}
