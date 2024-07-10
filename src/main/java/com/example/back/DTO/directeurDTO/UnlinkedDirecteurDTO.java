package com.example.back.DTO.directeurDTO;

import com.example.back.entities.Directeur;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
public class UnlinkedDirecteurDTO {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private String adresse;

    private String telephone;

    public UnlinkedDirecteurDTO(Integer id, String nom, String prenom, String email, String motDePasse, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public UnlinkedDirecteurDTO(Directeur directeur) {
        if (directeur == null) {return;}
        this.id = directeur.getId();
        this.nom = directeur.getNom();
        this.prenom = directeur.getPrenom();
        this.email = directeur.getEmail();
        this.motDePasse = directeur.getMotDePasse();
        this.adresse = directeur.getAdresse();
        this.telephone = directeur.getTelephone();
    }

}
