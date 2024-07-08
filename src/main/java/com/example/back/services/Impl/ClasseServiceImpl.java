package com.example.back.services.Impl;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.ProfesseurDTO;
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
    public ClasseDTO create(Classe classe) throws Exception {
         if(saveCheck(classe)) classeRepository.save(classe);
         return new ClasseDTO(classe);
    }
    private boolean saveCheck(Classe classe) throws Exception {
        if(classe.getNom() == null || classe.getNom().isEmpty()){
            throw new EmptyFieldsException(List.of("nom"), "classe");
        }
        boolean existsbyNom = classeRepository.existsByNom(classe.getNom());
        if (existsbyNom) {
            throw new AlreadyExistsException(List.of("nom"), "classe");
        }
        return true;
    }

    @Override
    public List<ClasseDTO> readAll() {
        return DTOListMapper.mapClasse(classeRepository.findAll());
    }

    @Override
    public Classe read(Integer id) throws Exception {
        Classe classe = classeRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
        return classe;
    }

    @Override
    public void update(Classe classe) throws Exception {
        Classe classe1 = classeRepository.findById(classe.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
        saveCheck(classe1);
        classe1.setNom(classe.getNom());
        classeRepository.save(classe1);
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!classeRepository.existsById(id)){
            throw new NotExistsException(List.of("id"), "classe");
        }
        classeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ClasseDTO> searchByNom(String nom) {
        return DTOListMapper.mapClasse(classeRepository.findByNomContains(nom));
    }

    @Override
    public Classe findByNom(String nom) {
        return classeRepository.findByNom(nom);
    }

    @Override
    public List<ClasseDTO> findByProfesseurId(Integer professeurId) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        return DTOListMapper.mapClasse(professeur.getClasses());
    }

    @Override
    public List<ProfesseurDTO> getProfesseursClasse(Integer id) throws NotExistsException {
        Classe classe = classeRepository.findById(id).orElseThrow(()-> new NotExistsException(List.of(), "classe"));
        return DTOListMapper.mapProfesseur(classe.getProfesseurs());
    }

    @Override
    public List<EtudiantDTO> getEtudiantsClasse(Integer id) throws NotExistsException {
        Classe classe = classeRepository.findById(id).orElseThrow(()-> new NotExistsException(List.of(), "classe"));
        return DTOListMapper.mapEtudiant(classe.getEtudiants());
    }

    @Override
    public boolean linkProfesseurs(Integer matiereId, List<Integer> professeurs) throws NotExistsException, AlreadyExistsException {
        Classe classe = classeRepository.findById(matiereId).orElseThrow(()->new NotExistsException(List.of(), "classe"));
        for (Integer professeurId : professeurs) {
            Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()-> new NotExistsException(List.of(), "professeur"));
            if(classe.getProfesseurs().contains(professeur) && professeur.getClasses().contains(classe)){
                throw new AlreadyExistsException(List.of(), "classe_professeur");
            }
            classe.addProfesseur(professeur);
            professeur.addClasse(classe);
            professeurRepository.save(professeur);
        }
        classeRepository.save(classe);
        return true;
    }

    @Override
    public boolean unlinkProfesseurs(Integer matiereId, List<Integer> professeurs) throws NotExistsException {
        Classe classe = classeRepository.findById(matiereId).orElseThrow(()->new NotExistsException(List.of(), "classe"));
        for (Integer professeurId : professeurs) {
            Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()-> new NotExistsException(List.of(), "professeur"));
            if(!classe.getProfesseurs().contains(professeur) || !professeur.getClasses().contains(classe)){
                throw new NotExistsException(List.of(), "classe_professeur");
            }
            professeur.removeClasse(classe);
            professeurRepository.save(professeur);
            classe.removeProfesseur(professeur);
        }
        classeRepository.save(classe);
        return true;
    }

    @Override
    public Classe findByEtudiantId(Integer id) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(()->new NotExistsException(List.of(), "etudiant"));
        return etudiant.getClasse();
    }

    @Override
    public boolean linkEtudiants(Integer matiereId, List<Integer> etudiants) throws NotExistsException, AlreadyExistsException {
        Classe classe = classeRepository.findById(matiereId).orElseThrow(()->new NotExistsException(List.of(), "classe"));
        for (Integer etudiantId : etudiants) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(()-> new NotExistsException(List.of(), "etudiant"));
            if(classe.getEtudiants().contains(etudiant) && etudiant.getClasse() == classe){
                throw new AlreadyExistsException(List.of(), "classe_etudiant");
            }
            classe.addEtudiant(etudiant);
            etudiant.setClasse(classe);
            etudiantRepository.save(etudiant);
        }
        classeRepository.save(classe);
        return true;
    }

    @Override
    public boolean unlinkEtudiants(Integer matiereId, List<Integer> etudiants) throws NotExistsException  {
        Classe classe = classeRepository.findById(matiereId).orElseThrow(()->new NotExistsException(List.of(), "classe"));
        for (Integer etudiantId : etudiants) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(()-> new NotExistsException(List.of(), "etudiant"));
            if(!classe.getEtudiants().contains(etudiant) || etudiant.getClasse() != classe){
                throw new NotExistsException(List.of(), "classe_etudiant");
            }
            etudiant.setClasse(null);
            etudiantRepository.save(etudiant);
            classe.removeEtudiant(etudiant);
        }
        classeRepository.save(classe);
        return true;
    }


}
