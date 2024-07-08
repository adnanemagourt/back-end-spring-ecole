package com.example.back.services.Impl;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Classe;
import com.example.back.entities.Etudiant;
import com.example.back.entities.Matiere;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.exceptions.UnAuthorizedUpdateException;
import com.example.back.repository.ClasseRepository;
import com.example.back.repository.EtudiantRepository;
import com.example.back.repository.MatiereRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public EtudiantDTO create(EtudiantDTO etudiantdto) throws Exception {
        Etudiant etudiant = saveCheck(etudiantdto);

        if (etudiantdto.getClasse_id() != null && etudiantdto.getClasse_id() > 0) {
            Classe classe = classeRepository.findById(etudiantdto.getClasse_id()).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
            classe.addEtudiant(etudiant);
            etudiant.setClasse(classe);
            classeRepository.save(classe);
        }

        etudiantRepository.save(etudiant);
        return etudiantdto;
    }

    @Override
    public List<EtudiantDTO> readAll() {
        return DTOListMapper.mapEtudiant(etudiantRepository.findBy());
    }

    @Override
    public Etudiant read(Integer id) throws Exception {
        Optional<Etudiant> t = etudiantRepository.findById(id);
        return t.orElseThrow(() -> new NotExistsException(List.of("id"), "etudiant"));
    }

    @Override
    public boolean update(EtudiantDTO etudiantdto) throws Exception {
        Etudiant old = etudiantRepository.findById(etudiantdto.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "etudiant"));
        if (!old.getNom().equals(etudiantdto.getNom()) || !old.getPrenom().equals(etudiantdto.getPrenom())) {
            throw new UnAuthorizedUpdateException(List.of("nom", "prenom"), "etudiant");
        }
        Etudiant newEtudiant = saveCheck(etudiantdto);


        if (etudiantdto.getClasse_id() != null && etudiantdto.getClasse_id() > 0) {
            Classe classe = classeRepository.findById(etudiantdto.getClasse_id()).orElseThrow(() -> new NotExistsException(List.of("id"), "classe"));
            newEtudiant.setClasse(classe);
            classe.addEtudiant(newEtudiant);
            classeRepository.save(classe);
            if (old.getClasse() != null && !Objects.equals(old.getClasse().getId(), etudiantdto.getClasse_id())) {
                Classe classe1 = old.getClasse();
                classe1.removeEtudiant(old);
                classeRepository.save(classe1);
            }
        } else {
            Classe classe = old.getClasse();
            if (classe != null) {
                classe.removeEtudiant(old);
                classeRepository.save(classe);
            }
        }
        etudiantRepository.save(newEtudiant);
        return true;
    }

    private static List<String> getEmptyFields(EtudiantDTO etudiant) {
        List<String> emptyFields = new java.util.ArrayList<>(List.of());
        if (etudiant.getNom() == null || etudiant.getNom().isEmpty()) {
            emptyFields.add("nom");
        }
        if (etudiant.getPrenom() == null || etudiant.getPrenom().isEmpty()) {
            emptyFields.add("prenom");
        }
        if (etudiant.getEmail() == null || etudiant.getEmail().isEmpty()) {
            emptyFields.add("email");
        }
        if (etudiant.getMotDePasse() == null || etudiant.getMotDePasse().isEmpty()) {
            emptyFields.add("motDePasse");
        }
        return emptyFields;
    }

    private Etudiant saveCheck(EtudiantDTO etudiantdto) throws Exception {
        List<String> emptyFields = getEmptyFields(etudiantdto);
        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldsException(emptyFields, "etudiant");
        }
        if (etudiantRepository.existsByEmail(etudiantdto.getEmail())) {
            throw new AlreadyExistsException(List.of("email"), "etudiant");
        }
        if (etudiantRepository.existsByNomAndPrenom(etudiantdto.getNom(), etudiantdto.getPrenom())) {
            throw new AlreadyExistsException(List.of("nom", "prenom"), "etudiant");
        }

        return Etudiant.builder()
                .id(etudiantdto.getId())
                .nom(etudiantdto.getNom())
                .prenom(etudiantdto.getPrenom())
                .email(etudiantdto.getEmail())
                .motDePasse(etudiantdto.getMotDePasse())
                .adresse(etudiantdto.getAdresse())
                .telephone(etudiantdto.getTelephone())
                .classe(null)
                .build();
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if (!etudiantRepository.existsById(id)) {
            throw new NotExistsException(List.of("id"), "etudiant");
        }
        etudiantRepository.deleteById(id);
        return true;
    }

    @Override
    public List<EtudiantDTO> searchByNom(String nom) {
        return DTOListMapper.mapEtudiant(etudiantRepository.findByNomContains(nom));
    }

    @Override
    public List<EtudiantDTO> getByClasseId(Integer classeId) throws Exception {
        if (!classeRepository.existsById(classeId)) {
            throw new NotExistsException(List.of("id"), "classe");
        }
        return DTOListMapper.mapEtudiant(etudiantRepository.findEtudiantsByClasse_Id(classeId));
    }

    @Override
    public List<EtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception {
        if (!professeurRepository.existsById(professeurId)) {
            throw new NotExistsException(List.of("id"), "professeur");
        }
        return DTOListMapper.mapEtudiant(etudiantRepository.getEtudiantsByProfesseurid(professeurId));
    }

    @Override
    public Etudiant getByEmail(String email) {
        return etudiantRepository.findEtudiantByEmail(email);
    }

    @Override
    public List<MatiereDTO> getEtudiantMatieres(Integer id) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        return DTOListMapper.mapMatiere(etudiant.getMatieres());

    }

    @Override
    public boolean linkMatieres(Integer etudiantId, List<Integer> matieres) throws AlreadyExistsException, NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        for (Integer matiereId : matieres) {
            Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
            if (matiere.getEtudiants().contains(etudiant) && etudiant.getMatieres().contains(matiere)) {
                throw new AlreadyExistsException(List.of(), "etudiant_matiere");
            }
            etudiant.addMatiere(matiere);
            matiere.addEtudiant(etudiant);
            matiereRepository.save(matiere);
        }
        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public boolean unlinkMatieres(Integer etudiantId, List<Integer> matieres) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        for (Integer matiereId : matieres) {
            Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
            if (!matiere.getEtudiants().contains(etudiant) || !etudiant.getMatieres().contains(matiere)) {
                throw new NotExistsException(List.of(), "etudiant_matiere");
            }
            matiere.removeEtudiant(etudiant);
            matiereRepository.save(matiere);
            etudiant.removeMatiere(matiere);
        }
        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public ClasseDTO getClasseOfEtudiant(Integer id) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        return new ClasseDTO(etudiant.getClasse());

    }

    @Override
    public boolean linkClasse(Integer etudiantId, Integer classeId) throws NotExistsException, AlreadyExistsException {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        Classe classe = classeRepository.findById(classeId).orElseThrow(() -> new NotExistsException(List.of(), "classe"));

        if (classe.getEtudiants().contains(etudiant) && etudiant.getClasse() == classe) {
            throw new AlreadyExistsException(List.of(), "etudiant_classe");
        }


        // remove if there is another classe before
        Classe classeOld = etudiant.getClasse();
        if (classeOld != null && classeOld.getEtudiants().contains(etudiant)) {
            classeOld.removeEtudiant(etudiant);
            classeRepository.save(classeOld);
        }

        classe.addEtudiant(etudiant);
        etudiant.setClasse(classe);
        classeRepository.save(classe);
        etudiantRepository.save(etudiant);

        return true;
    }

    @Override
    public boolean unlinkClasse(Integer etudiantId) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        Classe classe = etudiant.getClasse();

        if (classe == null) {
            return false;
        }

        classe.removeEtudiant(etudiant);
        classeRepository.save(classe);

        etudiant.setClasse(null);
        etudiantRepository.save(etudiant);

        return true;
    }
}
