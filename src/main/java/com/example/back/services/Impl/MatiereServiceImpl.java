package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.MatiereDTO;
import com.example.back.DTO.ProfesseurDTO;
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
    public MatiereDTO create(Matiere matiere) throws Exception {
        if (saveCheck(matiere)) matiereRepository.save(matiere);
        return new MatiereDTO(matiere);
    }

    @Override
    public boolean update(Matiere matiere) throws Exception {
        Matiere oldMatiere = matiereRepository.findById(matiere.getId()).orElseThrow(() -> new Exception("matiere id n'existe pas"));
        if (saveCheck(matiere)) {
            oldMatiere.setNom(matiere.getNom());
            matiereRepository.save(oldMatiere);
            return true;
        }
        return false;
    }

    private boolean saveCheck(Matiere matiere) throws Exception {
        if (matiere.getNom() == null || matiere.getNom().isEmpty()) {
            throw new EmptyFieldsException(List.of("nom"), "matiere");
        }
        if (matiereRepository.existsByNom(matiere.getNom())) {
            throw new AlreadyExistsException(List.of("nom"), "matiere");
        }
        return true;
    }

    @Override
    public List<MatiereDTO> readAll() {
        return DTOListMapper.mapMatiere(matiereRepository.findAll());
    }

    @Override
    public Matiere read(Integer id) throws Exception {
        Optional<Matiere> t = matiereRepository.findById(id);
        return t.orElseThrow(() -> new NotExistsException(List.of("id"), "matiere"));
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
    public List<MatiereDTO> searchByNom(String nom) {
        return DTOListMapper.mapMatiere(matiereRepository.findByNomContains(nom));

    }

    @Override
    public List<EtudiantDTO> getEtudiantsMatiere(Integer id) throws NotExistsException {
        Matiere matiere = matiereRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        return DTOListMapper.mapEtudiant(matiere.getEtudiants());
    }

    @Override
    public boolean linkEtudiants(Integer matiereId, List<Integer> etudiants) throws AlreadyExistsException, NotExistsException {
        Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        for (Integer etudiantId : etudiants) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
            if (matiere.getEtudiants().contains(etudiant) && etudiant.getMatieres().contains(matiere)) {
                throw new AlreadyExistsException(List.of(), "etudiant_matiere");
            }
            etudiant.addMatiere(matiere);
            matiere.addEtudiant(etudiant);
            etudiantRepository.save(etudiant);
        }
        matiereRepository.save(matiere);
        return true;
    }

    @Override
    public boolean unlinkEtudiants(Integer matiereId, List<Integer> etudiants) throws NotExistsException {
        Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        for (Integer etudiantId : etudiants) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
            if (!matiere.getEtudiants().contains(etudiant) || !etudiant.getMatieres().contains(matiere)) {
                throw new NotExistsException(List.of(), "etudiant_matiere");
            }
            matiere.removeEtudiant(etudiant);
            etudiant.removeMatiere(matiere);
            etudiantRepository.save(etudiant);
        }
        matiereRepository.save(matiere);
        return true;
    }

    @Override
    public List<ProfesseurDTO> getProfesseursMatiere(Integer id) throws NotExistsException {
        Matiere matiere = matiereRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        return DTOListMapper.mapProfesseur(matiere.getProfesseurs());
    }

    @Override
    public boolean linkProfesseurs(Integer matiereId, List<Integer> professeurs) throws AlreadyExistsException, NotExistsException {
        Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        for (Integer professeurId : professeurs) {
            Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
            if (matiere.getProfesseurs().contains(professeur) && professeur.getMatiere() == matiere) {
                throw new AlreadyExistsException(List.of(), "professeur_matiere");
            }
            professeur.setMatiere(matiere);
            matiere.addProfesseur(professeur);
            professeurRepository.save(professeur);
        }
        matiereRepository.save(matiere);
        return true;
    }

    @Override
    public boolean unlinkProfesseurs(Integer matiereId, List<Integer> professeurs) throws NotExistsException {
        Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
        for (Integer professeurId : professeurs) {
            Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
            if (!matiere.getProfesseurs().contains(professeur) || professeur.getMatiere() != matiere) {
                throw new NotExistsException(List.of(), "professeur_matiere");
            }
            matiere.removeProfesseur(professeur);
            professeur.setMatiere(null);
            professeurRepository.save(professeur);
        }
        matiereRepository.save(matiere);
        return true;
    }
}
