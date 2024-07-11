package com.example.back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "professeur")
public class Professeur {
    @Column(name = "nom")
    public String nom;
    @Column(name = "prenom")
    public String prenom;
    @Column(name = "email")
    public String email;
    @Column(name = "adresse")
    public String adresse;
    @Column(name = "telephone")
    public String telephone;
    @ManyToOne
    @JoinColumn(
            name = "matiere_id",
            referencedColumnName = "id"
    )
    public Matiere matiere;
    @ManyToMany(mappedBy = "professeurs")
    public List<Classe> classes;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "mot_de_passe")
    private String motDePasse;

    public boolean addClasse(Classe classe) {
        if (classes == null) {
            classes = new ArrayList<>();
        }
        return classes.add(classe);
    }

    public boolean removeClasse(Classe classe) {
        if (classes == null) {
            return false;
        }
        return classes.remove(classe);
    }


}
