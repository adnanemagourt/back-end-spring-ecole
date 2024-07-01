package com.example.back.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "etudiant")
public class Etudiant extends Personne {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "nom")
    public String nom;

    @Column(name = "prenom")
    public String prenom;

    @Column(name = "email")
    public String email;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "date_naissance")
    public Date dateNaissance;

    @Column(name = "adresse")
    public String adresse;

    @Column(name = "telephone")
    public String telephone;

    //@Column(name = "classe_id")
    //private Integer classeId;

    @ManyToOne
    @JoinColumn(
            name = "classe_id",
            referencedColumnName = "id"
    )
    public Classe classe;

    //    public Integer getClasseId() { return classeId; }

//    public void setClasseId(Integer classeId) { this.classeId = classeId; }

}
