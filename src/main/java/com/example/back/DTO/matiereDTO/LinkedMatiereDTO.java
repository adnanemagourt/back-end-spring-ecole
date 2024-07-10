package com.example.back.DTO.matiereDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.professeurDTO.ProfesseurDTO;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.entities.Matiere;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Data
public class LinkedMatiereDTO extends MatiereDTO{

    private List<UnlinkedEtudiantDTO> etudiants;

    private List<UnlinkedProfesseurDTO> professeurs;

    public LinkedMatiereDTO(Integer id, String nom, List<UnlinkedEtudiantDTO> etudiants, List<UnlinkedProfesseurDTO> professeurs) {
        super(id, nom);
        this.etudiants = etudiants;
        this.professeurs = professeurs;
    }

    public LinkedMatiereDTO(Matiere matiere) {
        super(matiere);
        if (matiere == null) {return;}
        this.etudiants = DTOListMapper.mapUnlinkedEtudiant(matiere.etudiants);
        this.professeurs = DTOListMapper.mapUnlinkedProfesseur(matiere.professeurs);
    }

}
