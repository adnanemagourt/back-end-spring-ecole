package com.example.back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "matiere")
public class Matiere {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

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


}
