package com.example.back.controllers;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.MatiereDTO;
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
    public ResponseEntity<Etudiant> login(@RequestBody EtudiantDTO etudiant) throws Exception {
        Etudiant found = etudiantService.getByEmail(etudiant.getEmail());
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
    public ResponseEntity<EtudiantDTO> addEtudiant(@RequestBody EtudiantDTO etudiant) throws Exception {
        EtudiantDTO etudiant1 = etudiantService.create(etudiant);
        return new ResponseEntity<>(etudiant1, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateEtudiant(@RequestBody EtudiantDTO etudiant) throws Exception {
        etudiantService.update(etudiant);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<EtudiantDTO>> getEtudiant() {
        List<EtudiantDTO> etudiants = etudiantService.readAll();
        return etudiants != null ? new ResponseEntity<>(etudiants, HttpStatus.OK) : new ResponseEntity<>((List<EtudiantDTO>) null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable("id") Integer id) throws Exception {
        Etudiant etudiant = etudiantService.read(id);
        return new ResponseEntity<>(etudiant, HttpStatus.OK);
    }
    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiant(@PathVariable("nom") String nom) {
        List<EtudiantDTO> etudiants = etudiantService.searchByNom(nom);
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
    public ResponseEntity<List<EtudiantDTO>> findEtudiantsByClasseid(@PathVariable("id") Integer id) throws Exception {
        List<EtudiantDTO> etudiants = etudiantService.getByClasseId(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @GetMapping(path = "/professeur/{id}")
    public ResponseEntity<List<EtudiantDTO>> findEtudiantsByProfesseurid(@PathVariable("id") Integer id) throws Exception {
        List<EtudiantDTO> etudiants = etudiantService.getByProfesseurId(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }


    // get matieres of etudiant
    @GetMapping(path = "/{id}/matieres")
    public ResponseEntity<List<MatiereDTO>> getMatiresEtudiant(@PathVariable(name = "id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(etudiantService.getEtudiantMatieres(id), HttpStatus.OK);
    }

    // link matieres to etudiant
    @PostMapping(path = "/{id}/matieres")
    public ResponseEntity<String> linkMatieres(@PathVariable(name = "id") Integer id, @RequestBody List<Integer> matieres) throws AlreadyExistsException, NotExistsException {
        etudiantService.linkMatieres(id, matieres);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // unlink matieres from etudiant
    @DeleteMapping(path = "/{id}/matieres")
    public ResponseEntity<String> unlinkMatieres(@PathVariable(name = "id") Integer id, @RequestBody List<Integer> matieres) throws AlreadyExistsException, NotExistsException {
        etudiantService.unlinkMatieres(id, matieres);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get classe of etudiant
    @GetMapping(path = "/{id}/classe")
    public ResponseEntity<ClasseDTO> getClasseByEtudiantId(@PathVariable(name = "id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(etudiantService.getClasseOfEtudiant(id), HttpStatus.OK);
    }

    // link classe to etudiant
    @PostMapping(path = "/{id}/classe/{classeId}")
    public ResponseEntity<String> linkClasse(@PathVariable(name = "id") Integer id, @PathVariable(name = "classeId") Integer classeId) throws AlreadyExistsException, NotExistsException {
        etudiantService.linkClasse(id, classeId);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // unlink classe from etudiant
    @PostMapping(path = "/{id}/classe")
    public ResponseEntity<String> unlinkClasse(@PathVariable(name = "id") Integer id) throws NotExistsException {
        etudiantService.unlinkClasse(id);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

}
