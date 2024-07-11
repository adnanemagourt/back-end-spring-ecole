package com.example.back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "matiere")
public class Matiere {
    @Column(name = "nom")
    public String nom;
    @OneToMany(mappedBy = "matiere")
    public List<Professeur> professeurs;
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "etudiants_matieres",
            joinColumns = @JoinColumn(
                    name = "matiere_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "etudiant_id",
                    referencedColumnName = "id"
            )
    )
    public List<Etudiant> etudiants;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public boolean addProfesseur(Professeur professeur) {
        if (this.professeurs == null) {
            this.professeurs = new ArrayList<>();
        }
        return this.professeurs.add(professeur);
    }

    public boolean removeProfesseur(Professeur professeur) {
        if (this.professeurs != null) {
            return this.professeurs.remove(professeur);
        }
        return false;
    }

    public boolean addEtudiant(Etudiant etudiant) {
        if (this.etudiants == null) {
            this.etudiants = new ArrayList<>();
        }
        return this.etudiants.add(etudiant);
    }

    public boolean removeEtudiant(Etudiant etudiant) {
        if (this.etudiants != null) {
            return this.etudiants.remove(etudiant);
        }
        return false;
    }


}
