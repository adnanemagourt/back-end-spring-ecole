package com.example.back.DTO;

import com.example.back.DTO.classeDTO.ClasseDTO;
import com.example.back.DTO.classeDTO.LinkedClasseDTO;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.directeurDTO.DirecteurDTO;
import com.example.back.DTO.directeurDTO.LinkedDirecteurDTO;
import com.example.back.DTO.directeurDTO.UnlinkedDirecteurDTO;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.DTO.etudiantDTO.LinkedEtudiantDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.LinkedMatiereDTO;
import com.example.back.DTO.matiereDTO.MatiereDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.DTO.professeurDTO.LinkedProfesseurDTO;
import com.example.back.DTO.professeurDTO.ProfesseurDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.entities.*;

import java.util.ArrayList;
import java.util.List;

public class DTOListMapper {
    public static List<EtudiantDTO> mapEtudiant(List<Etudiant> iter) {
        List<EtudiantDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Etudiant et : iter) {
            list.add(new EtudiantDTO(et));
        }
        return list;
    }

    public static List<Integer> mapEtudiantIds(List<Etudiant> iter) {
        List<Integer> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Etudiant et : iter) {
            list.add(et.getId());
        }
        return list;
    }

    public static List<LinkedEtudiantDTO> mapLinkedEtudiant(List<Etudiant> iter) {
        List<LinkedEtudiantDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Etudiant et : iter) {
            list.add(new LinkedEtudiantDTO(et));
        }
        return list;
    }

    public static List<UnlinkedEtudiantDTO> mapUnlinkedEtudiant(List<Etudiant> iter) {
        List<UnlinkedEtudiantDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Etudiant et : iter) {
            list.add(new UnlinkedEtudiantDTO(et));
        }
        return list;
    }

    public static List<ClasseDTO> mapClasse(List<Classe> iter) {
        List<ClasseDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Classe c : iter) {
            list.add(new ClasseDTO(c));
        }
        return list;
    }

    public static List<Integer> mapClasseIds(List<Classe> iter) {
        List<Integer> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Classe c : iter) {
            list.add(c.getId());
        }
        return list;
    }

    public static List<LinkedClasseDTO> mapLinkedClasse(List<Classe> iter) {
        List<LinkedClasseDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Classe c : iter) {
            list.add(new LinkedClasseDTO(c));
        }
        return list;
    }

    public static List<UnlinkedClasseDTO> mapUnlinkedClasse(List<Classe> iter) {
        List<UnlinkedClasseDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Classe c : iter) {
            list.add(new UnlinkedClasseDTO(c));
        }
        return list;
    }

    public static List<DirecteurDTO> mapDirecteur(List<Directeur> iter) {
        List<DirecteurDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Directeur d : iter) {
            list.add(new DirecteurDTO(d));
        }
        return list;
    }

    public static List<Integer> mapDirecteurIds(List<Directeur> iter) {
        List<Integer> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Directeur d : iter) {
            list.add(d.getId());
        }
        return list;
    }

    public static List<LinkedDirecteurDTO> mapLinkedDirecteur(List<Directeur> iter) {
        List<LinkedDirecteurDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Directeur d : iter) {
            list.add(new LinkedDirecteurDTO(d));
        }
        return list;
    }

    public static List<UnlinkedDirecteurDTO> mapUnlinkedDirecteur(List<Directeur> iter) {
        List<UnlinkedDirecteurDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Directeur d : iter) {
            list.add(new UnlinkedDirecteurDTO(d));
        }
        return list;
    }

    public static List<MatiereDTO> mapMatiere(List<Matiere> iter) {
        List<MatiereDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Matiere m : iter) {
            list.add(new MatiereDTO(m));
        }
        return list;
    }

    public static List<Integer> mapMatiereIds(List<Matiere> iter) {
        List<Integer> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Matiere m : iter) {
            list.add(m.getId());
        }
        return list;
    }

    public static List<LinkedMatiereDTO> mapLinkedMatiere(List<Matiere> iter) {
        List<LinkedMatiereDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Matiere m : iter) {
            list.add(new LinkedMatiereDTO(m));
        }
        return list;
    }

    public static List<UnlinkedMatiereDTO> mapUnlinkedMatiere(List<Matiere> iter) {
        List<UnlinkedMatiereDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Matiere m : iter) {
            list.add(new UnlinkedMatiereDTO(m));
        }
        return list;
    }

    public static List<ProfesseurDTO> mapProfesseur(List<Professeur> iter) {
        List<ProfesseurDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Professeur p : iter) {
            list.add(new ProfesseurDTO(p));
        }
        return list;
    }

    public static List<Integer> mapProfesseurIds(List<Professeur> iter) {
        List<Integer> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Professeur p : iter) {
            list.add(p.getId());
        }
        return list;
    }

    public static List<LinkedProfesseurDTO> mapLinkedProfesseur(List<Professeur> iter) {
        List<LinkedProfesseurDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Professeur p : iter) {
            list.add(new LinkedProfesseurDTO(p));
        }
        return list;
    }

    public static List<UnlinkedProfesseurDTO> mapUnlinkedProfesseur(List<Professeur> iter) {
        List<UnlinkedProfesseurDTO> list = new ArrayList<>();
        if (iter == null || iter.isEmpty()) {
            return list;
        }
        for (Professeur p : iter) {
            list.add(new UnlinkedProfesseurDTO(p));
        }
        return list;
    }

}
