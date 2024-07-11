package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.LinkedMatiereDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.entities.Etudiant;
import com.example.back.entities.Matiere;
import com.example.back.entities.Professeur;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.repository.EtudiantRepository;
import com.example.back.repository.MatiereRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatiereServiceImpl implements MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;

    @Override
    public LinkedMatiereDTO create(UnlinkedMatiereDTO matieredto) throws Exception {
        if (saveCheck(matieredto)) {
            Matiere matiere = Matiere.builder()
                    .nom(matieredto.getNom())
                    .build();
            link(matiere, matieredto);
            matiereRepository.save(matiere);
            return new LinkedMatiereDTO(matiere);
        }
        return null;
    }

    @Override
    public void update(UnlinkedMatiereDTO matieredto) throws Exception {
        Matiere oldMatiere = matiereRepository.findById(matieredto.getId()).orElseThrow(() -> new Exception("matiere id n'existe pas"));
        if (saveCheck(matieredto)) {
            link(oldMatiere, matieredto);
            oldMatiere.setNom(matieredto.getNom());
            matiereRepository.save(oldMatiere);
        }
    }

    private boolean saveCheck(UnlinkedMatiereDTO matiere) throws Exception {
        if (matiere.getNom() == null || matiere.getNom().isEmpty()) {
            throw new EmptyFieldsException(List.of("nom"), "matiere");
        }
        if (matiereRepository.existsByNom(matiere.getNom())) {
            throw new AlreadyExistsException(List.of("nom"), "matiere");
        }
        return true;
    }

    @Override
    public List<UnlinkedMatiereDTO> readAll() {
        return DTOListMapper.mapUnlinkedMatiere(matiereRepository.findAll());
    }

    @Override
    public LinkedMatiereDTO read(Integer id) throws Exception {
        Optional<Matiere> t = matiereRepository.findById(id);
        return new LinkedMatiereDTO(t.orElseThrow(() -> new NotExistsException(List.of("id"), "matiere")));
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if (!matiereRepository.existsById(id)) {
            throw new NotExistsException(List.of("id"), "matiere");
        }
        matiereRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UnlinkedMatiereDTO> searchByNom(String nom) {
        return DTOListMapper.mapUnlinkedMatiere(matiereRepository.findByNomContains(nom));

    }

    @Override
    public List<UnlinkedEtudiantDTO> getEtudiantsMatiere(Integer id) throws NotExistsException {
        Matiere matiere = matiereRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        return DTOListMapper.mapUnlinkedEtudiant(matiere.getEtudiants());
    }

    private void link(Matiere matiere, UnlinkedMatiereDTO matieredto) throws AlreadyExistsException, NotExistsException {
        //link etudiants
        List<Etudiant> oldListEtudiants = matiere.getEtudiants();
        if(oldListEtudiants != null){
            for(Etudiant etudiant : oldListEtudiants){
                if(!matieredto.getEtudiants().contains(etudiant.getId())){
                    etudiant.removeMatiere(matiere);
                    etudiantRepository.save(etudiant);
                }
            }
        }
        matiere.setEtudiants(new ArrayList<>());
        for (Integer etudiantId : matieredto.getEtudiants()) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
            if (matiere.getEtudiants().contains(etudiant) && etudiant.getMatieres().contains(matiere)) {
                throw new AlreadyExistsException(List.of("etudiant_id", "matiere_id"), "etudiant_matiere");
            }
            matiere.addEtudiant(etudiant);
            if(oldListEtudiants != null && oldListEtudiants.contains(etudiant)) continue;
            etudiant.addMatiere(matiere);
            etudiantRepository.save(etudiant);
        }

        //link professeurs
        List<Professeur> oldListProfesseurs = matiere.getProfesseurs();
        if(oldListProfesseurs != null){
            for(Professeur professeur : oldListProfesseurs){
                if(!matieredto.getProfesseurs().contains(professeur.getId())){
                    professeur.setMatiere(null);
                    professeurRepository.save(professeur);
                }
            }
        }
        matiere.setProfesseurs(new ArrayList<>());
        for (Integer professeurId : matieredto.getProfesseurs()) {
            Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
            if (matiere.getProfesseurs().contains(professeur) && professeur.getMatiere() == matiere) {
                throw new AlreadyExistsException(List.of("professeur_id", "matiere_id"), "professeur_matiere");
            }
            matiere.addProfesseur(professeur);
            if(oldListProfesseurs != null && oldListProfesseurs.contains(professeur)) continue;
            professeur.setMatiere(matiere);
            professeurRepository.save(professeur);
        }
    }

    @Override
    public List<UnlinkedProfesseurDTO> getProfesseursMatiere(Integer id) throws NotExistsException {
        Matiere matiere = matiereRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        return DTOListMapper.mapUnlinkedProfesseur(matiere.getProfesseurs());
    }

}
