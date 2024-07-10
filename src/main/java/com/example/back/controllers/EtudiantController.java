package com.example.back.controllers;

import com.example.back.DTO.classeDTO.ClasseDTO;
import com.example.back.DTO.etudiantDTO.EtudiantDTO;
import com.example.back.DTO.etudiantDTO.LinkedEtudiantDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.MatiereDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.entities.Etudiant;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;


    // login
    @PostMapping(path = "/login")
    public ResponseEntity<LinkedEtudiantDTO> login(@RequestBody EtudiantDTO etudiant) throws Exception {
        LinkedEtudiantDTO found = etudiantService.getByEmail(etudiant.getEmail());
        if (found == null) {
            throw new Exception("not found");
        }
        if (!Objects.equals(found.getMotDePasse(), etudiant.getMotDePasse())) {
            throw new Exception("wrong password");
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    // insert
    @PostMapping(path = "")
    public ResponseEntity<EtudiantDTO> addEtudiant(@RequestBody UnlinkedEtudiantDTO etudiant) throws Exception {
        EtudiantDTO etudiant1 = etudiantService.create(etudiant);
        return new ResponseEntity<>(etudiant1, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateEtudiant(@RequestBody UnlinkedEtudiantDTO etudiant) throws Exception {
        etudiantService.update(etudiant);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<UnlinkedEtudiantDTO>> getEtudiant() {
        List<UnlinkedEtudiantDTO> etudiants = etudiantService.readAll();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<LinkedEtudiantDTO> getEtudiant(@PathVariable("id") Integer id) throws Exception {
        LinkedEtudiantDTO etudiant = etudiantService.read(id);
        return new ResponseEntity<>(etudiant, HttpStatus.OK);
    }
    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<UnlinkedEtudiantDTO>> getEtudiant(@PathVariable("nom") String nom) {
        List<UnlinkedEtudiantDTO> etudiants = etudiantService.searchByNom(nom);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable("id") Integer id) throws Exception {
        etudiantService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    //autre

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<UnlinkedEtudiantDTO>> findEtudiantsByClasseid(@PathVariable("id") Integer id) throws Exception {
        List<UnlinkedEtudiantDTO> etudiants = etudiantService.getByClasseId(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @GetMapping(path = "/professeur/{id}")
    public ResponseEntity<List<UnlinkedEtudiantDTO>> findEtudiantsByProfesseurid(@PathVariable("id") Integer id) throws Exception {
        List<UnlinkedEtudiantDTO> etudiants = etudiantService.getByProfesseurId(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }


    // get matieres of etudiant
    @GetMapping(path = "/{id}/matieres")
    public ResponseEntity<List<UnlinkedMatiereDTO>> getMatiresEtudiant(@PathVariable(name = "id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(etudiantService.getEtudiantMatieres(id), HttpStatus.OK);
    }

    // get classe of etudiant
    @GetMapping(path = "/{id}/classe")
    public ResponseEntity<ClasseDTO> getClasseByEtudiantId(@PathVariable(name = "id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(etudiantService.getClasseOfEtudiant(id), HttpStatus.OK);
    }

}
