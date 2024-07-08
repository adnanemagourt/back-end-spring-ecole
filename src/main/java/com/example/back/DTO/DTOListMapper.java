package com.example.back.DTO;

import com.example.back.entities.*;

import java.util.ArrayList;
import java.util.List;

public class DTOListMapper {
    public static List<EtudiantDTO> mapEtudiant(List<Etudiant> iter){
        List<EtudiantDTO> list = new ArrayList<>();
        if(iter==null || iter.isEmpty()){
            return list;
        }
        for(Etudiant et : iter){
            list.add(new EtudiantDTO(et));
        }
        return list;
    }

    public static List<ClasseDTO> mapClasse(List<Classe> iter){
        List<ClasseDTO> list = new ArrayList<>();
        if(iter==null || iter.isEmpty()){
            return list;
        }
        for(Classe c : iter){
            list.add(new ClasseDTO(c));
        }
        return list;
    }

    public static List<DirecteurDTO> mapDirecteur(List<Directeur> iter){
        List<DirecteurDTO> list = new ArrayList<>();
        if(iter==null || iter.isEmpty()){
            return list;
        }
        for(Directeur d : iter){
            list.add(new DirecteurDTO(d));
        }
        return list;
    }

    public static List<MatiereDTO> mapMatiere(List<Matiere> iter){
        List<MatiereDTO> list = new ArrayList<>();
        if(iter==null || iter.isEmpty()){
            return list;
        }
        for(Matiere m : iter){
            list.add(new MatiereDTO(m));
        }
        return list;
    }

    public static List<ProfesseurDTO> mapProfesseur(List<Professeur> iter){
        List<ProfesseurDTO> list = new ArrayList<>();
        if(iter==null || iter.isEmpty()){
            return list;
        }
        for(Professeur p : iter){
            list.add(new ProfesseurDTO(p));
        }
        return list;
    }

}
