package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Matiere;
import com.example.back.entities.Professeur;
import com.example.back.repository.EtudiantRepository;
import com.example.back.repository.MatiereRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public boolean create(ProfesseurDTO professeurdto) throws Exception {
        return saveCheck(professeurdto);
    }

    private boolean saveCheck(ProfesseurDTO professeurdto) throws Exception {
        if(professeurdto.getNom() == null || professeurdto.getNom().isEmpty() || professeurdto.getPrenom() == null || professeurdto.getPrenom().isEmpty() || professeurdto.getEmail() == null || professeurdto.getEmail().isEmpty() || professeurdto.getMotDePasse() == null || professeurdto.getMotDePasse().isEmpty()){
            throw new Exception("important fields are empty");
        }

        if(professeurRepository.findProfesseurByEmail(professeurdto.getEmail()) != null){
            throw new Exception("email already exist");
        }
        if(professeurRepository.findByNomAndPrenom(professeurdto.getNom(), professeurdto.getPrenom()) != null){
            throw new Exception("nom & prenom already exist");
        }

        Matiere matiere = new Matiere();
        if(professeurdto.getMatiere_id()!=null && professeurdto.getMatiere_id()>0){
            matiere = matiereRepository.findById(professeurdto.getMatiere_id()).orElseThrow(()->new Exception("matiere id does not exist"));
        }

        Professeur professeur = Professeur.builder()
                .id(professeurdto.getId())
                .nom(professeurdto.getNom())
                .prenom(professeurdto.getPrenom())
                .email(professeurdto.getEmail())
                .motDePasse(professeurdto.getMotDePasse())
                .adresse(professeurdto.getAdresse())
                .telephone(professeurdto.getTelephone())
                .matiere(matiere)
                .build();

        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public List<ProfesseurDTO> readAll() {
        return DTOListMapper.mapProfesseur(professeurRepository.findBy());
    }

    @Override
    public ProfesseurDTO read(Integer id) throws Exception {
        Optional<Professeur> t = professeurRepository.findById(id);
        return new ProfesseurDTO(t.orElseThrow(()->new Exception("id not found")));
    }

    @Override
    public boolean update(ProfesseurDTO professeurdto) throws Exception {
        Professeur old = professeurRepository.findById(professeurdto.getId()).orElseThrow(()->new Exception("id not found"));
        if(!old.getNom().equals(professeurdto.getNom()) || !old.getPrenom().equals(professeurdto.getPrenom())){
            throw new Exception("can't update nom and prenom");
        }
        return saveCheck(professeurdto);
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!professeurRepository.existsById(id)){
            throw new Exception("id does not exist");
        }
        professeurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ProfesseurDTO> searchByNom(String nom) {
        return DTOListMapper.mapProfesseur(professeurRepository.findByNomContains(nom));
    }

    @Override
    public List<ProfesseurDTO> getByEtudiantId(Integer etudiantId) throws Exception {
        if(!etudiantRepository.existsById(etudiantId)){
            throw new Exception("etudiant id does not exist");
        }
        return DTOListMapper.mapProfesseur(professeurRepository.getProfesseursByEtudiantId(etudiantId));
    }

    @Override
    public ProfesseurDTO getByEmail(String email) {
        return new ProfesseurDTO(professeurRepository.findProfesseurByEmail(email));
    }
}
