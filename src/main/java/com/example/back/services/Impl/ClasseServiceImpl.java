package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.classeDTO.LinkedClasseDTO;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.entities.Classe;
import com.example.back.entities.Etudiant;
import com.example.back.entities.Professeur;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.repository.ClasseRepository;
import com.example.back.repository.EtudiantRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public LinkedClasseDTO create(UnlinkedClasseDTO classedto) throws Exception {
        if (saveCheck(classedto)) {
            Classe classe = Classe.builder()
                    .nom(classedto.getNom())
                    .build();
            link(classe, classedto);
            classeRepository.save(classe);
            return new LinkedClasseDTO(classe);
        }
        return null;
    }

    private boolean saveCheck(UnlinkedClasseDTO classe) throws Exception {
        if (classe.getNom() == null || classe.getNom().isEmpty()) {
            throw new EmptyFieldsException(List.of("nom"), "classe");
        }
        boolean existsbyNom = classeRepository.existsByNom(classe.getNom());
        if (existsbyNom) {
            throw new AlreadyExistsException(List.of("nom"), "classe");
        }
        return true;
    }

    @Override
    public List<UnlinkedClasseDTO> readAll() {
        return DTOListMapper.mapUnlinkedClasse(classeRepository.findAll());
    }

    @Override
    public LinkedClasseDTO read(Integer id) throws Exception {
        Classe classe = classeRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
        return new LinkedClasseDTO(classe);
    }

    @Override
    public void update(UnlinkedClasseDTO classedto) throws Exception {
        Classe classe = classeRepository.findById(classedto.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
        if (saveCheck(classedto)) {
            classe.setNom(classedto.getNom());
            link(classe, classedto);
            classeRepository.save(classe);
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if (!classeRepository.existsById(id)) {
            throw new NotExistsException(List.of("id"), "classe");
        }
        classeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UnlinkedClasseDTO> searchByNom(String nom) {
        return DTOListMapper.mapUnlinkedClasse(classeRepository.findByNomContains(nom));
    }

    @Override
    public LinkedClasseDTO findByNom(String nom) {
        return new LinkedClasseDTO(classeRepository.findByNom(nom));
    }

    @Override
    public List<UnlinkedClasseDTO> findByProfesseurId(Integer professeurId) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
        return DTOListMapper.mapUnlinkedClasse(professeur.getClasses());
    }

    private void link(Classe classe, UnlinkedClasseDTO classedto) throws AlreadyExistsException, NotExistsException {
        //link professeurs:
        classe.setProfesseurs(new ArrayList<>());
        for (Integer professeurId : classedto.getProfesseurs()) {
            Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
            if (classe.getProfesseurs().contains(professeur) && professeur.getClasses().contains(classe)) {
                throw new AlreadyExistsException(List.of("classe_id", "professeur_id"), "classe_professeur");
            }
            classe.addProfesseur(professeur);
            professeur.addClasse(classe);
            professeurRepository.save(professeur);
        }

        //link etudiants:
        classe.setEtudiants(new ArrayList<>());
        for (Integer etudiantId : classedto.getEtudiants()) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
            if (classe.getEtudiants().contains(etudiant) && etudiant.getClasse() == classe) {
                throw new AlreadyExistsException(List.of("classe_id", "etudiant_id"), "classe_etudiant");
            }
            classe.addEtudiant(etudiant);
            etudiant.setClasse(classe);
            etudiantRepository.save(etudiant);
        }
    }

    @Override
    public List<UnlinkedProfesseurDTO> getProfesseursClasse(Integer id) throws NotExistsException {
        Classe classe = classeRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "classe"));
        return DTOListMapper.mapUnlinkedProfesseur(classe.getProfesseurs());
    }

    @Override
    public List<UnlinkedEtudiantDTO> getEtudiantsClasse(Integer id) throws NotExistsException {
        Classe classe = classeRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "classe"));
        return DTOListMapper.mapUnlinkedEtudiant(classe.getEtudiants());
    }

    @Override
    public LinkedClasseDTO findByEtudiantId(Integer id) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        return new LinkedClasseDTO(etudiant.getClasse());
    }

}
