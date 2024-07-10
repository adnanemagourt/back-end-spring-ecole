package com.example.back.DTO.etudiantDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.entities.Classe;
import com.example.back.entities.Etudiant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
public class UnlinkedEtudiantDTO extends EtudiantDTO{

    private Integer classe_id;
    
    private List<Integer> matieres;

    public UnlinkedEtudiantDTO(Integer id, String nom, String prenom, String email, String motDePasse, Date dateNaissance, String adresse, String telephone, Integer classe_id, List<Integer> matieres) {
        super(id, nom, prenom, email, motDePasse, dateNaissance, adresse, telephone);
        this.classe_id = classe_id;
        this.matieres = matieres;
    }

    public UnlinkedEtudiantDTO(Etudiant etudiant){
        super(etudiant);
        if(etudiant == null){return;}
        Classe classe = etudiant.getClasse();
        this.classe_id = classe == null ? null : classe.getId();
        this.matieres = DTOListMapper.mapMatiereIds(etudiant.getMatieres());
    }


}
