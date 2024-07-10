package com.example.back.DTO.classeDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.professeurDTO.ProfesseurDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.entities.Classe;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class LinkedClasseDTO extends ClasseDTO {

    private List<UnlinkedEtudiantDTO> etudiants;

    private List<UnlinkedProfesseurDTO> professeurs;

    public LinkedClasseDTO(Integer id, String nom, List<UnlinkedEtudiantDTO> etudiants, List<UnlinkedProfesseurDTO> professeurs) {
        super(id, nom);
        this.etudiants = etudiants;
        this.professeurs = professeurs;
    }

    public LinkedClasseDTO(Classe classe) {
        super(classe);
        this.etudiants = DTOListMapper.mapUnlinkedEtudiant(classe.etudiants);
        this.professeurs = DTOListMapper.mapUnlinkedProfesseur(classe.professeurs);
    }
}
