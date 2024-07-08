package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Classe;
import com.example.back.entities.Matiere;
import com.example.back.entities.Professeur;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.exceptions.UnAuthorizedUpdateException;
import com.example.back.repository.ClasseRepository;
import com.example.back.repository.EtudiantRepository;
import com.example.back.repository.MatiereRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public ProfesseurDTO create(ProfesseurDTO professeurdto) throws Exception {
        Professeur professeur = saveCheck(professeurdto);
        if (professeurdto.getMatiere_id() != null && professeurdto.getMatiere_id() > 0) {
            Matiere matiere = matiereRepository.findById(professeurdto.getMatiere_id()).orElseThrow(() -> new NotExistsException(List.of("id"), "matiere"));
            matiere.addProfesseur(professeur);
            professeur.setMatiere(matiere);
            matiereRepository.save(matiere);
        }
        professeurRepository.save(professeur);
        return professeurdto;
    }

    private static List<String> getEmptyFields(ProfesseurDTO professeur) {
        List<String> emptyFields = new java.util.ArrayList<>(List.of());
        if(professeur.getNom() == null || professeur.getNom().isEmpty()){
            emptyFields.add("nom");
        }
        if(professeur.getPrenom() == null || professeur.getPrenom().isEmpty()){
            emptyFields.add("prenom");
        }
        if(professeur.getEmail() == null || professeur.getEmail().isEmpty()){
            emptyFields.add("email");
        }
        if(professeur.getMotDePasse() == null || professeur.getMotDePasse().isEmpty()){
            emptyFields.add("motDePasse");
        }
        return emptyFields;
    }


    private Professeur saveCheck(ProfesseurDTO professeurdto) throws Exception {
        List<String> emptyFields = getEmptyFields(professeurdto);
        if(!emptyFields.isEmpty()){
            throw new EmptyFieldsException(emptyFields, "professeur");
        }

        if (professeurRepository.existsByEmail(professeurdto.getEmail())) {
            throw new AlreadyExistsException(List.of("email"), "professeur");
        }
        if (professeurRepository.existsByNomAndPrenom(professeurdto.getNom(), professeurdto.getPrenom())) {
            throw new AlreadyExistsException(List.of("nom", "prenom"), "professeur");
        }

        return Professeur.builder()
                .id(professeurdto.getId())
                .nom(professeurdto.getNom())
                .prenom(professeurdto.getPrenom())
                .email(professeurdto.getEmail())
                .motDePasse(professeurdto.getMotDePasse())
                .adresse(professeurdto.getAdresse())
                .telephone(professeurdto.getTelephone())
                .matiere(null)
                .build();

    }

    @Override
    public List<ProfesseurDTO> readAll() {
        return DTOListMapper.mapProfesseur(professeurRepository.findBy());
    }

    @Override
    public ProfesseurDTO read(Integer id) throws Exception {
        Optional<Professeur> t = professeurRepository.findById(id);
        return new ProfesseurDTO(t.orElseThrow(() -> new NotExistsException(List.of("id"), "professeur")));
    }

    @Override
    public boolean update(ProfesseurDTO professeurdto) throws Exception {
        Professeur old = professeurRepository.findById(professeurdto.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "matiere"));
        if (!old.getNom().equals(professeurdto.getNom()) || !old.getPrenom().equals(professeurdto.getPrenom())) {
            throw new UnAuthorizedUpdateException(List.of("nom", "prenom"), "professeur");
        }
        Professeur professeur = saveCheck(professeurdto);

        if (professeurdto.getMatiere_id() != null && professeurdto.getMatiere_id() > 0) {
            Matiere matiere = matiereRepository.findById(professeurdto.getMatiere_id()).orElseThrow(() -> new NotExistsException(List.of("id"), "matiere"));
            professeur.setMatiere(matiere);
            matiere.addProfesseur(professeur);
            matiereRepository.save(matiere);
            if (old.getMatiere() != null && !Objects.equals(old.getMatiere().getId(), professeurdto.getMatiere_id())) {
                Matiere matiere1 = old.getMatiere();
                matiere1.removeProfesseur(old);
                matiereRepository.save(matiere1);
            }
        } else {
            Matiere matiere = old.getMatiere();
            if (matiere != null) {
                matiere.removeProfesseur(old);
                matiereRepository.save(matiere);
            }
        }

        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if (!professeurRepository.existsById(id)) {
            throw new NotExistsException(List.of("id"), "professeur");
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
        if (!etudiantRepository.existsById(etudiantId)) {
            throw new NotExistsException(List.of("id"), "etudiant");
        }
        return DTOListMapper.mapProfesseur(professeurRepository.getProfesseursByEtudiantId(etudiantId));
    }

    @Override
    public ProfesseurDTO getByEmail(String email) {
        return new ProfesseurDTO(professeurRepository.findProfesseurByEmail(email));
    }

    @Override
    public List<Classe> findProfesseurClasses(Integer id) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(id).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        return professeur.getClasses();
    }

    @Override
    public boolean linkClasses(Integer professeurId, List<Integer> classes) throws NotExistsException, AlreadyExistsException {
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        for (Integer classeId : classes) {
            Classe classe = classeRepository.findById(classeId).orElseThrow(()->new NotExistsException(List.of(), "classe"));
            if(classe.getProfesseurs().contains(professeur) && professeur.getClasses().contains(classe)){
                throw new AlreadyExistsException(List.of(), "professeur_classe");
            }
            professeur.addClasse(classe);
            classe.addProfesseur(professeur);
            classeRepository.save(classe);
        }
        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public boolean unlinkClasses(Integer professeurId, List<Integer> classes) throws NotExistsException, AlreadyExistsException {
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        for (Integer classeId : classes) {
            Classe classe = classeRepository.findById(classeId).orElseThrow(()->new NotExistsException(List.of(), "classe"));
            if(!classe.getProfesseurs().contains(professeur) || !professeur.getClasses().contains(classe)){
                throw new AlreadyExistsException(List.of(), "professeur_classe");
            }
            classe.removeProfesseur(professeur);
            classeRepository.save(classe);
            professeur.removeClasse(classe);
        }
        professeurRepository.save(professeur);
        return true;
    }

    @Override
    public Matiere findProfesseurMatiere(Integer id) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(id).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        return professeur.getMatiere();
    }

    @Override
    public boolean linkMatiere(Integer professeurId, Integer matiereId) throws NotExistsException, AlreadyExistsException {
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(()-> new NotExistsException(List.of(), "matiere"));

        if(matiere.getProfesseurs().contains(professeur) && professeur.getMatiere() == matiere){
            throw new AlreadyExistsException(List.of(), "professeur_matiere");
        }


        // remove if there is another matiere before
        Matiere matiereOld = professeur.getMatiere();
        if(matiereOld != null && matiereOld.getProfesseurs().contains(professeur)){
            matiereOld.removeProfesseur(professeur);
            matiereRepository.save(matiereOld);
        }

        matiere.addProfesseur(professeur);
        professeur.setMatiere(matiere);
        matiereRepository.save(matiere);
        professeurRepository.save(professeur);

        return true;

    }

    @Override
    public boolean unlinkMatiere(Integer professeurId) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(()->new NotExistsException(List.of(), "professeur"));
        Matiere matiere = professeur.getMatiere();

        if(matiere == null){
            return false;
        }

        matiere.removeProfesseur(professeur);
        matiereRepository.save(matiere);

        professeur.setMatiere(null);
        professeurRepository.save(professeur);

        return true;
    }

}
