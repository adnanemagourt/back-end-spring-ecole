package com.example.back.DTO.professeurDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.entities.Matiere;
import com.example.back.entities.Professeur;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Data
public class LinkedProfesseurDTO extends ProfesseurDTO {
    
    private UnlinkedMatiereDTO matiere;

    private List<UnlinkedClasseDTO> classes;
    
    public LinkedProfesseurDTO(Integer id, String nom, String prenom, String email, String motDePasse, String adresse, String telephone, UnlinkedMatiereDTO matiere, List<UnlinkedClasseDTO> classes) {
        super(id, nom, prenom, email, motDePasse, adresse, telephone);
        this.matiere = matiere;
        this.classes = classes;
    }

    public LinkedProfesseurDTO(Professeur professeur) {
        super(professeur);
        if(professeur == null){return;}
        UnlinkedMatiereDTO matiere = new UnlinkedMatiereDTO(professeur.getMatiere());
        this.matiere = matiere ;
        this.classes = DTOListMapper.mapUnlinkedClasse(professeur.getClasses());
    }

}
