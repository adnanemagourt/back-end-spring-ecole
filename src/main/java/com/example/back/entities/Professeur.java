package com.example.back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "professeur")
public class Professeur {
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

    @Setter
    @Getter
    @Column(name = "prenom")
    public String prenom;

    @Setter
    @Getter
    @Column(name = "email")
    public String email;

    @Setter
    @Getter
    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Setter
    @Getter
    @Column(name = "adresse")
    public String adresse;

    @Setter
    @Getter
    @Column(name = "telephone")
    public String telephone;

//    @Column(name = "matiere_id")
//    private Integer matiereId;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(
            name = "matiere_id",
            referencedColumnName = "id"
    )
    public Matiere matiere;

    @Getter
    @ManyToMany(mappedBy = "professeurs")
    public List<Classe> classes;



    //    public Integer getMatiereId() { return matiereId; }

//    public void setMatiereId(Integer classeId) { this.matiereId = classeId; }

}
