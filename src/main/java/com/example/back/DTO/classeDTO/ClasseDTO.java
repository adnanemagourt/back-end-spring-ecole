package com.example.back.DTO.classeDTO;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.DTO.professeurDTO.ProfesseurDTO;
import com.example.back.entities.Classe;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ClasseDTO {

    private Integer id;

    private String nom;

    public ClasseDTO() {}

    public ClasseDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public ClasseDTO(Classe classe) {
        if (classe == null) {
        }else {
            this.id = classe.getId();
            this.nom = classe.getNom();
        }
    }
}
