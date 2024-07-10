package com.example.back.DTO.professeurDTO;

import com.example.back.entities.Matiere;
import com.example.back.entities.Professeur;
import lombok.*;

@Data
public class ProfesseurDTO {

    private Integer id;

    public String nom;

    public String prenom;

    public String email;

    private String motDePasse;

    public String adresse;

    public String telephone;

    public ProfesseurDTO() {}

    public ProfesseurDTO(Integer id, String nom, String prenom, String email, String motDePasse, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public ProfesseurDTO(Professeur professeur) {
        if(professeur == null){return;}
        this.id = professeur.getId();
        this.nom = professeur.getNom();
        this.prenom = professeur.getPrenom();
        this.email = professeur.getEmail();
        this.motDePasse = professeur.getMotDePasse();
        this.adresse = professeur.getAdresse();
        this.telephone = professeur.getTelephone();
    }

}