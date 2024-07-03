package com.example.back.DTO;

import com.example.back.entities.*;

import java.util.ArrayList;
import java.util.List;

public class DTOListMapper {
    public static List<EtudiantDTO> mapEtudiant(Iterable<Etudiant> iter){
        List<EtudiantDTO> list = new ArrayList<>();
        for(Etudiant et : iter){
            list.add(new EtudiantDTO(et));
        }
        return list;
    }

    public static List<ClasseDTO> mapClasse(Iterable<Classe> iter){
        List<ClasseDTO> list = new ArrayList<>();
        for(Classe c : iter){
            list.add(new ClasseDTO(c));
        }
        return list;
    }

    public static List<DirecteurDTO> mapDirecteur(Iterable<Directeur> iter){
        List<DirecteurDTO> list = new ArrayList<>();
        for(Directeur d : iter){
            list.add(new DirecteurDTO(d));
        }
        return list;
    }

    public static List<MatiereDTO> mapMatiere(Iterable<Matiere> iter){
        List<MatiereDTO> list = new ArrayList<>();
        for(Matiere m : iter){
            list.add(new MatiereDTO(m));
        }
        return list;
    }

    public static List<ProfesseurDTO> mapProfesseur(Iterable<Professeur> iter){
        List<ProfesseurDTO> list = new ArrayList<>();
        for(Professeur p : iter){
            list.add(new ProfesseurDTO(p));
        }
        return list;
    }

}