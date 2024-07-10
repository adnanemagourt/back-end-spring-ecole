package com.example.back.DTO.etudiantDTO;

import com.example.back.entities.Classe;
import com.example.back.entities.Etudiant;
import lombok.*;
import java.util.Date;

@Data
public class EtudiantDTO {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private Date dateNaissance;

    private String adresse;

    private String telephone;

    public EtudiantDTO() {}

    public EtudiantDTO(Integer id, String nom, String prenom, String email, String motDePasse, Date dateNaissance, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public EtudiantDTO(Etudiant etudiant){
        if(etudiant == null){return;}
        this.id = etudiant.getId();
        this.nom = etudiant.getNom();
        this.prenom = etudiant.getPrenom();
        this.email = etudiant.getEmail();
        this.motDePasse = etudiant.getMotDePasse();
        this.dateNaissance = etudiant.getDateNaissance();
        this.adresse = etudiant.getAdresse();
        this.telephone = etudiant.getTelephone();
    }


}
