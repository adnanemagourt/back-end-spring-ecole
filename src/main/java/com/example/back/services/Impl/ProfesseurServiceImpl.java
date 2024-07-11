package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.matiereDTO.LinkedMatiereDTO;
import com.example.back.DTO.professeurDTO.LinkedProfesseurDTO;
import com.example.back.DTO.professeurDTO.ProfesseurDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
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

import java.util.ArrayList;
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
    @Autowired
    private ClasseRepository classeRepository;

    private static List<String> getEmptyFields(ProfesseurDTO professeur) {
        List<String> emptyFields = new java.util.ArrayList<>(List.of());
        if (professeur.getNom() == null || professeur.getNom().isEmpty()) {
            emptyFields.add("nom");
        }
        if (professeur.getPrenom() == null || professeur.getPrenom().isEmpty()) {
            emptyFields.add("prenom");
        }
        if (professeur.getEmail() == null || professeur.getEmail().isEmpty()) {
            emptyFields.add("email");
        }
        if (professeur.getMotDePasse() == null || professeur.getMotDePasse().isEmpty()) {
            emptyFields.add("motDePasse");
        }
        return emptyFields;
    }

    @Override
    public LinkedProfesseurDTO create(UnlinkedProfesseurDTO professeurdto) throws Exception {
        saveCheck(professeurdto);

        Professeur professeur = Professeur.builder()
                .id(professeurdto.getId())
                .nom(professeurdto.getNom())
                .prenom(professeurdto.getPrenom())
                .email(professeurdto.getEmail())
                .motDePasse(professeurdto.getMotDePasse())
                .adresse(professeurdto.getAdresse())
                .telephone(professeurdto.getTelephone())
                .matiere(null)
                .build();

        linkClasses(professeur, professeurdto);
        linkMatiere(professeur, professeurdto);

        professeurRepository.save(professeur);
        return new LinkedProfesseurDTO(professeur);
    }

    private void saveCheck(ProfesseurDTO professeurdto) throws Exception {
        List<String> emptyFields = getEmptyFields(professeurdto);
        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldsException(emptyFields, "professeur");
        }

        if (professeurRepository.existsByEmail(professeurdto.getEmail())) {
            throw new AlreadyExistsException(List.of("email"), "professeur");
        }
        if (professeurRepository.existsByNomAndPrenom(professeurdto.getNom(), professeurdto.getPrenom())) {
            throw new AlreadyExistsException(List.of("nom", "prenom"), "professeur");
        }
    }

    @Override
    public List<UnlinkedProfesseurDTO> readAll() {
        return DTOListMapper.mapUnlinkedProfesseur(professeurRepository.findBy());
    }

    @Override
    public LinkedProfesseurDTO read(Integer id) throws Exception {
        Optional<Professeur> t = professeurRepository.findById(id);
        return new LinkedProfesseurDTO(t.orElseThrow(() -> new NotExistsException(List.of("id"), "professeur")));
    }

    @Override
    public boolean update(UnlinkedProfesseurDTO professeurdto) throws Exception {
        Professeur oldProfesseur = professeurRepository.findById(professeurdto.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "matiere"));
        if (!oldProfesseur.getNom().equals(professeurdto.getNom()) || !oldProfesseur.getPrenom().equals(professeurdto.getPrenom())) {
            throw new UnAuthorizedUpdateException(List.of("nom", "prenom"), "professeur");
        }

        saveCheck(professeurdto);

        linkClasses(oldProfesseur, professeurdto);
        linkMatiere(oldProfesseur, professeurdto);

        oldProfesseur.setEmail(professeurdto.getEmail());
        oldProfesseur.setMotDePasse(professeurdto.getMotDePasse());
        oldProfesseur.setTelephone(professeurdto.getTelephone());
        oldProfesseur.setAdresse(professeurdto.getAdresse());

        professeurRepository.save(oldProfesseur);
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
    public List<UnlinkedProfesseurDTO> searchByNom(String nom) {
        return DTOListMapper.mapUnlinkedProfesseur(professeurRepository.findByNomContains(nom));
    }

    @Override
    public List<UnlinkedProfesseurDTO> getByEtudiantId(Integer etudiantId) throws Exception {
        if (!etudiantRepository.existsById(etudiantId)) {
            throw new NotExistsException(List.of("id"), "etudiant");
        }
        return DTOListMapper.mapUnlinkedProfesseur(professeurRepository.getProfesseursByEtudiantId(etudiantId));
    }

    @Override
    public LinkedProfesseurDTO getByEmail(String email) {
        return new LinkedProfesseurDTO(professeurRepository.findProfesseurByEmail(email));
    }

    @Override
    public List<UnlinkedClasseDTO> findProfesseurClasses(Integer id) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
        return DTOListMapper.mapUnlinkedClasse(professeur.getClasses());
    }

    private void linkClasses(Professeur professeur, UnlinkedProfesseurDTO professeurDTO) throws NotExistsException, AlreadyExistsException {
        List<Classe> oldListClasses = professeur.getClasses();
        if (oldListClasses != null) {
            for (Classe classe : oldListClasses) {
                if (!professeurDTO.getClasses().contains(classe.getId())) {
                    classe.removeProfesseur(professeur);
                    classeRepository.save(classe);
                }
            }
        }
        professeur.setClasses(new ArrayList<>());
        for (Integer classeId : professeurDTO.getClasses()) {
            Classe classe = classeRepository.findById(classeId).orElseThrow(() -> new NotExistsException(List.of(), "classe"));
            if (classe.getProfesseurs().contains(professeur) && professeur.getClasses().contains(classe)) {
                throw new AlreadyExistsException(List.of("professeur_id", "classe_id"), "professeur_classe");
            }
            professeur.addClasse(classe);
            if (oldListClasses != null && oldListClasses.contains(classe)) continue;
            classe.addProfesseur(professeur);
            classeRepository.save(classe);
        }
    }

    @Override
    public LinkedMatiereDTO findProfesseurMatiere(Integer id) throws NotExistsException {
        Professeur professeur = professeurRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "professeur"));
        return new LinkedMatiereDTO(professeur.getMatiere());
    }

    private void linkMatiere(Professeur oldProfesseur, UnlinkedProfesseurDTO professeurDTO) throws NotExistsException, AlreadyExistsException {
        Integer matiereId = professeurDTO.getMatiere_id();
        if (matiereId != null && matiereId > 0) {
            Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
            if (matiere.getProfesseurs().contains(oldProfesseur) && oldProfesseur.getMatiere() == matiere) {
                throw new AlreadyExistsException(List.of("professeur_id", "matiere_id"), "professeur_matiere");
            }

            // remove if there is another matiere before
            Integer oldMatiereId = unlinkMatiere(oldProfesseur);

            oldProfesseur.setMatiere(matiere);
            if (!oldMatiereId.equals(matiere.getId()) || oldMatiereId == -1) matiere.addProfesseur(oldProfesseur);
            matiereRepository.save(matiere);
        }
    }

    private Integer unlinkMatiere(Professeur professeur) {
        Matiere matiere = professeur.getMatiere();

        if (matiere == null || !matiere.getProfesseurs().contains(professeur)) {
            return -1;
        }

        matiere.removeProfesseur(professeur);
        matiereRepository.save(matiere);

        professeur.setMatiere(null);

        return matiere.getId();
    }

}
