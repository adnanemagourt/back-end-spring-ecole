package com.example.back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "etudiant")
public class Etudiant {

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

    @ManyToMany(mappedBy = "etudiants")
    public List<Matiere> matieres;

    @ManyToOne
    @JoinColumn(
            name = "classe_id",
            referencedColumnName = "id"
    )
    public Classe classe;



}
