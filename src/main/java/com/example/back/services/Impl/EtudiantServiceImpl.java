package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Classe;
import com.example.back.entities.Etudiant;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.EmptyFieldsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.exceptions.UnAuthorizedUpdateException;
import com.example.back.repository.ClasseRepository;
import com.example.back.repository.EtudiantRepository;
import com.example.back.repository.ProfesseurRepository;
import com.example.back.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public EtudiantDTO create(EtudiantDTO etudiantdto) throws Exception {
        etudiantRepository.save(saveCheck(etudiantdto));
        return etudiantdto;
    }

    @Override
    public List<EtudiantDTO> readAll() {
        return DTOListMapper.mapEtudiant(etudiantRepository.findBy());
    }

    @Override
    public EtudiantDTO read(Integer id) throws Exception {
        Optional<Etudiant> t = etudiantRepository.findById(id);
        return new EtudiantDTO(t.orElseThrow(()->new NotExistsException(List.of("id"), "etudiant")));
    }

    @Override
    public boolean update(EtudiantDTO etudiantdto) throws Exception {
        Etudiant old = etudiantRepository.findById(etudiantdto.getId()).orElseThrow(()->new NotExistsException(List.of("id"), "etudiant"));
        if(!old.getNom().equals(etudiantdto.getNom()) || !old.getPrenom().equals(etudiantdto.getPrenom())){
            throw new UnAuthorizedUpdateException(List.of("nom", "prenom"), "etudiant");
        }
        Etudiant newEtudiant = saveCheck(etudiantdto);
        etudiantRepository.save(newEtudiant);
        return true;
    }

    private static List<String> getEmptyFields(EtudiantDTO etudiant) {
        List<String> emptyFields = new java.util.ArrayList<>(List.of());
        if(etudiant.getNom() == null || etudiant.getNom().isEmpty()){
            emptyFields.add("nom");
        }
        if(etudiant.getPrenom() == null || etudiant.getPrenom().isEmpty()){
            emptyFields.add("prenom");
        }
        if(etudiant.getEmail() == null || etudiant.getEmail().isEmpty()){
            emptyFields.add("email");
        }
        if(etudiant.getMotDePasse() == null || etudiant.getMotDePasse().isEmpty()){
            emptyFields.add("motDePasse");
        }
        return emptyFields;
    }

    private Etudiant saveCheck(EtudiantDTO etudiantdto) throws Exception {
        List<String> emptyFields = getEmptyFields(etudiantdto);
        if(!emptyFields.isEmpty()){
            throw new EmptyFieldsException(emptyFields, "etudiant");
        }
        if(etudiantRepository.existsByEmail(etudiantdto.getEmail())){
            throw new AlreadyExistsException(List.of("email"), "etudiant");
        }
        if(etudiantRepository.existsByNomAndPrenom(etudiantdto.getNom(), etudiantdto.getPrenom())){
            throw new AlreadyExistsException(List.of("nom", "prenom"), "etudiant");
        }

        Classe classe = null;
        if(etudiantdto.getClasse_id()!=null && etudiantdto.getClasse_id()>0){
            classe = classeRepository.findById(etudiantdto.getClasse_id()).orElseThrow(()->new NotExistsException(List.of("id"), "classe"));
        }

        return Etudiant.builder()
                .id(etudiantdto.getId())
                .nom(etudiantdto.getNom())
                .prenom(etudiantdto.getPrenom())
                .email(etudiantdto.getEmail())
                .motDePasse(etudiantdto.getMotDePasse())
                .adresse(etudiantdto.getAdresse())
                .telephone(etudiantdto.getTelephone())
                .classe(classe)
                .build();
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!etudiantRepository.existsById(id)){
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
        if(!classeRepository.existsById(classeId)){
            throw new NotExistsException(List.of("id"), "classe");
        }
        return DTOListMapper.mapEtudiant(etudiantRepository.findEtudiantsByClasse_Id(classeId));
    }

    @Override
    public List<EtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception {
        if(!professeurRepository.existsById(professeurId)){
            throw new NotExistsException(List.of("id"), "professeur");
        }
        return DTOListMapper.mapEtudiant(etudiantRepository.getEtudiantsByProfesseurid(professeurId));
    }

    @Override
    public EtudiantDTO getByEmail(String email) {
        return new EtudiantDTO(etudiantRepository.findEtudiantByEmail(email));
    }
}
