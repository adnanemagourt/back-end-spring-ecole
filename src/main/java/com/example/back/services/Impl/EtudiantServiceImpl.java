package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.classeDTO.LinkedClasseDTO;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.DTO.etudiantDTO.LinkedEtudiantDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
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

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public LinkedEtudiantDTO create(UnlinkedEtudiantDTO etudiantdto) throws Exception {
        saveCheck(etudiantdto);
        Etudiant etudiant = Etudiant.builder()
                .id(etudiantdto.getId())
                .nom(etudiantdto.getNom())
                .prenom(etudiantdto.getPrenom())
                .email(etudiantdto.getEmail())
                .motDePasse(etudiantdto.getMotDePasse())
                .adresse(etudiantdto.getAdresse())
                .telephone(etudiantdto.getTelephone())
                .classe(null)
                .build();

        linkMatieres(etudiant, etudiantdto);
        linkClasse(etudiant, etudiantdto);

        etudiantRepository.save(etudiant);
        return new LinkedEtudiantDTO(etudiant);
    }

    @Override
    public List<UnlinkedEtudiantDTO> readAll() {
        return DTOListMapper.mapUnlinkedEtudiant(etudiantRepository.findBy());
    }

    @Override
    public LinkedEtudiantDTO read(Integer id) throws Exception {
        Optional<Etudiant> t = etudiantRepository.findById(id);
        return new LinkedEtudiantDTO(t.orElseThrow(() -> new NotExistsException(List.of("id"), "etudiant")));
    }

    @Override
    public boolean update(UnlinkedEtudiantDTO etudiantdto) throws Exception {
        Etudiant oldEtudiant = etudiantRepository.findById(etudiantdto.getId()).orElseThrow(() -> new NotExistsException(List.of("id"), "etudiant"));
        if (!oldEtudiant.getNom().equals(etudiantdto.getNom()) || !oldEtudiant.getPrenom().equals(etudiantdto.getPrenom())) {
            throw new UnAuthorizedUpdateException(List.of("nom", "prenom"), "etudiant");
        }

        saveCheck(etudiantdto);

        linkMatieres(oldEtudiant, etudiantdto);
        linkClasse(oldEtudiant, etudiantdto);

        oldEtudiant.setEmail(etudiantdto.getEmail());
        oldEtudiant.setMotDePasse(etudiantdto.getMotDePasse());
        oldEtudiant.setTelephone(etudiantdto.getTelephone());
        oldEtudiant.setAdresse(etudiantdto.getAdresse());
        oldEtudiant.setDateNaissance(etudiantdto.getDateNaissance());

        etudiantRepository.save(oldEtudiant);
        return true;
    }

    private void saveCheck(EtudiantDTO etudiantdto) throws Exception {
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
    public List<UnlinkedEtudiantDTO> searchByNom(String nom) {
        return DTOListMapper.mapUnlinkedEtudiant(etudiantRepository.findByNomContains(nom));
    }

    @Override
    public List<UnlinkedEtudiantDTO> getByClasseId(Integer classeId) throws Exception {
        if (!classeRepository.existsById(classeId)) {
            throw new NotExistsException(List.of("id"), "classe");
        }
        return DTOListMapper.mapUnlinkedEtudiant(etudiantRepository.findEtudiantsByClasse_Id(classeId));
    }

    @Override
    public List<UnlinkedEtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception {
        if (!professeurRepository.existsById(professeurId)) {
            throw new NotExistsException(List.of("id"), "professeur");
        }
        return DTOListMapper.mapUnlinkedEtudiant(etudiantRepository.getEtudiantsByProfesseurid(professeurId));
    }

    @Override
    public LinkedEtudiantDTO getByEmail(String email) {
        return new LinkedEtudiantDTO(etudiantRepository.findEtudiantByEmail(email));
    }

    @Override
    public List<UnlinkedMatiereDTO> getEtudiantMatieres(Integer id) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        return DTOListMapper.mapUnlinkedMatiere(etudiant.getMatieres());

    }

    private void linkMatieres(Etudiant etudiant, UnlinkedEtudiantDTO etudiantDTO) throws AlreadyExistsException, NotExistsException {
        List<Matiere> oldListMatiere = etudiant.getMatieres();
        if(oldListMatiere!=null){
            for(Matiere matiere: oldListMatiere){
                if(!etudiantDTO.getMatieres().contains(matiere.getId())){
                    matiere.removeEtudiant(etudiant);
                    matiereRepository.save(matiere);
                }
            }
        }
        etudiant.setMatieres(new ArrayList<>());
        for (Integer matiereId : etudiantDTO.getMatieres()) {
            Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new NotExistsException(List.of(), "matiere"));
            if (matiere.getEtudiants().contains(etudiant) && etudiant.getMatieres().contains(matiere)) {
                throw new AlreadyExistsException(List.of("etudiant_id", "matiere_id"), "etudiant_matiere");
            }
            etudiant.addMatiere(matiere);
            if(oldListMatiere!=null && oldListMatiere.contains(matiere)) continue;
            matiere.addEtudiant(etudiant);
            matiereRepository.save(matiere);
        }
    }

    @Override
    public LinkedClasseDTO getClasseOfEtudiant(Integer id) throws NotExistsException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new NotExistsException(List.of(), "etudiant"));
        return new LinkedClasseDTO(etudiant.getClasse());

    }

    private void linkClasse(Etudiant oldEtudiant, UnlinkedEtudiantDTO etudiantdto) throws NotExistsException {
        Integer classeId = etudiantdto.getClasse_id();
        if (classeId != null && classeId > 0) {
            Classe classe = classeRepository.findById(classeId).orElseThrow(() -> new NotExistsException(List.of(), "classe"));
            if (classe.getEtudiants().contains(oldEtudiant) && oldEtudiant.getClasse() == classe) {
                return;
            }

            // remove if there is another different classe before
            Integer oldClasseId = unlinkClasse(oldEtudiant);

            oldEtudiant.setClasse(classe);
            if (!oldClasseId.equals(classe.getId()) || oldClasseId == -1) classe.addEtudiant(oldEtudiant);
            classeRepository.save(classe);
        }
    }

    private Integer unlinkClasse(Etudiant etudiant) {
        Classe classe = etudiant.getClasse();
        if (classe != null && classe.getEtudiants().contains(etudiant)) {

            classe.removeEtudiant(etudiant);
            classeRepository.save(classe);

            etudiant.setClasse(null);
            return classe.getId();
        }
        return -1;
    }

}
