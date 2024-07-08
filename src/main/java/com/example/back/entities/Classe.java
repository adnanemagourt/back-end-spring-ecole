package com.example.back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "classe")
public class Classe {
    @Setter
    @Getter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Setter
    @Getter
    @Column(name = "nom")
    public String nom;

    @Getter
    @OneToMany(mappedBy = "classe")
    public List<Etudiant> etudiants;

    public boolean addEtudiant(Etudiant etudiant) {
        if(etudiants == null){
            etudiants = new ArrayList<>();
        }
        return etudiants.add(etudiant);
    }
    public boolean removeEtudiant(Etudiant etudiant) {
        if(etudiants == null){
            return false;
        }
        return etudiants.remove(etudiant);
    }

    @Getter
    @Setter
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "professeur_classes",
            joinColumns = @JoinColumn(
                    name = "classe_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "professeur_id",
                    referencedColumnName = "id"
            )
    )
    public List<Professeur> professeurs;

    public void addProfesseur(Professeur professeur) {
        if (professeurs == null) {
            professeurs = new ArrayList<Professeur>();
        }
        professeurs.add(professeur);
    }

    public void removeProfesseur(Professeur professeur) {
        if(professeurs == null){
            return;
        }
        professeurs.remove(professeur);
    }

}
