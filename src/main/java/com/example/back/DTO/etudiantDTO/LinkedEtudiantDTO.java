package com.example.back.DTO.etudiantDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.entities.Etudiant;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LinkedEtudiantDTO extends EtudiantDTO {

    private UnlinkedClasseDTO classe;

    private List<UnlinkedMatiereDTO> matieres;

    public LinkedEtudiantDTO(Integer id, String nom, String prenom, String email, String motDePasse, Date dateNaissance, String adresse, String telephone, UnlinkedClasseDTO classe, List<UnlinkedMatiereDTO> matieres) {
        super(id, nom, prenom, email, motDePasse, dateNaissance, adresse, telephone);
        this.classe = classe;
        this.matieres = matieres;
    }

    public LinkedEtudiantDTO(Etudiant etudiant) {
        super(etudiant);
        if (etudiant == null) {
            return;
        }
        UnlinkedClasseDTO classe = new UnlinkedClasseDTO(etudiant.getClasse());
        this.classe = classe;
        this.matieres = DTOListMapper.mapUnlinkedMatiere(etudiant.getMatieres());
    }


}
