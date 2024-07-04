package com.example.back.services.Impl;

import com.example.back.DTO.DTOListMapper;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Classe;
import com.example.back.entities.Etudiant;
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
    public boolean create(EtudiantDTO etudiantdto) throws Exception {
        return saveCheck(etudiantdto);
    }

    @Override
    public List<EtudiantDTO> readAll() {
        return DTOListMapper.mapEtudiant(etudiantRepository.findBy());
    }

    @Override
    public EtudiantDTO read(Integer id) throws Exception {
        Optional<Etudiant> t = etudiantRepository.findById(id);
        return new EtudiantDTO(t.orElseThrow(()->new Exception("id does not exist")));
    }

    @Override
    public boolean update(EtudiantDTO etudiantdto) throws Exception {
        Etudiant old = etudiantRepository.findById(etudiantdto.getId()).orElseThrow(()->new Exception("id not found"));
        if(!old.getNom().equals(etudiantdto.getNom()) || !old.getPrenom().equals(etudiantdto.getPrenom())){
            throw new Exception("can't update nom and prenom");
        }
        return saveCheck(etudiantdto);
    }

    private boolean saveCheck(EtudiantDTO etudiantdto) throws Exception {
        if(etudiantdto.getNom() == null || etudiantdto.getNom().isEmpty() || etudiantdto.getPrenom() == null || etudiantdto.getPrenom().isEmpty() || etudiantdto.getEmail() == null || etudiantdto.getEmail().isEmpty() || etudiantdto.getMotDePasse() == null || etudiantdto.getMotDePasse().isEmpty()){
            throw new Exception("important fields are empty");
        }
        if(etudiantRepository.findEtudiantByEmail(etudiantdto.getEmail()) != null){
            throw new Exception("email already exists");
        }
        if(etudiantRepository.findByNomAndPrenom(etudiantdto.getNom(), etudiantdto.getPrenom()) != null){
            throw new Exception("nom & prenom already exists");
        }

        Classe classe = new Classe();
        if(etudiantdto.getClasse_id()!=null && etudiantdto.getClasse_id()>0){
            classe = classeRepository.findById(etudiantdto.getClasse_id()).orElseThrow(()->new Exception("matiere id does not exist"));
        }

        Etudiant etudiant = Etudiant.builder()
                .id(etudiantdto.getId())
                .nom(etudiantdto.getNom())
                .prenom(etudiantdto.getPrenom())
                .email(etudiantdto.getEmail())
                .motDePasse(etudiantdto.getMotDePasse())
                .adresse(etudiantdto.getAdresse())
                .telephone(etudiantdto.getTelephone())
                .classe(classe)
                .build();

        etudiantRepository.save(etudiant);
        return true;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        if(!etudiantRepository.existsById(id)){
            throw new Exception("id does not exist");
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
            throw new Exception("classe id does not exist");
        }
        return DTOListMapper.mapEtudiant(etudiantRepository.findEtudiantsByClasse_Id(classeId));
    }

    @Override
    public List<EtudiantDTO> getByProfesseurId(Integer professeurId) throws Exception {
        if(!professeurRepository.existsById(professeurId)){
            throw new Exception("professeur id does not exist");
        }
        return DTOListMapper.mapEtudiant(etudiantRepository.getEtudiantsByProfesseurid(professeurId));
    }

    @Override
    public EtudiantDTO getByEmail(String email) {
        return new EtudiantDTO(etudiantRepository.findEtudiantByEmail(email));
    }
}
