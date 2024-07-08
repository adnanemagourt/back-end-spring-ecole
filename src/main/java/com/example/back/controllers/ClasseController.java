package com.example.back.controllers;

import com.example.back.DTO.ClasseDTO;
import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Classe;
import com.example.back.exceptions.AlreadyExistsException;
import com.example.back.exceptions.NotExistsException;
import com.example.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/classe")
public class ClasseController {

    @Autowired
    private ClasseService classeService;


    // insert
    @PostMapping(path = "")
    public ResponseEntity<ClasseDTO> addClasse(@RequestBody Classe classe) throws Exception {
        ClasseDTO classe1 = classeService.create(classe);
        return new ResponseEntity<>(classe1, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateClasse(@RequestBody Classe classe) throws Exception {
        classeService.update(classe);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<ClasseDTO>> getClasse() {
        List<ClasseDTO> classes = classeService.readAll();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Classe> getClasse(@PathVariable("id") Integer id) throws Exception {
        Classe classe = classeService.read(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<ClasseDTO>> getClasse(@PathVariable("nom") String nom) {
        List<ClasseDTO> classes = classeService.searchByNom(nom);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClasse(@PathVariable("id") Integer id) throws Exception {
        classeService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    // get classes of professeur
    @GetMapping(path = "/professeur/{id}")
    public ResponseEntity<List<ClasseDTO>> getClassesProfesseur(@PathVariable("id") Integer id) throws NotExistsException {
        List<ClasseDTO> classes = classeService.findByProfesseurId(id);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    // get classe of etudiant
    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<Classe> getClasseOfEtudiant(@PathVariable("id") Integer id) throws NotExistsException {
        Classe classe = classeService.findByEtudiantId(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    // get professeurs of classe
    @GetMapping(path = "/{id}/professeurs")
    public ResponseEntity<List<ProfesseurDTO>> getProfesseursClasse(@PathVariable("id") Integer id) throws NotExistsException {
        List<ProfesseurDTO> professeurs = classeService.getProfesseursClasse(id);
        return new ResponseEntity<>(professeurs, HttpStatus.OK);
    }

    // link professeurs to classe
    @PostMapping(path="/{id}/professeurs")
    public ResponseEntity<String> linkProfesseurs(@PathVariable("id") Integer id, @RequestBody List<Integer> professeurs) throws NotExistsException, AlreadyExistsException {
        classeService.linkProfesseurs(id, professeurs);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // unlink professeurs from classe
    @DeleteMapping(path="/{id}/professeurs")
    public ResponseEntity<String> unlinkProfesseurs(@PathVariable("id") Integer id, @RequestBody List<Integer> professeurs) throws NotExistsException, AlreadyExistsException {
        classeService.unlinkProfesseurs(id, professeurs);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    //get Etudiants of classe
    @GetMapping(path = "/{id}/etudiants")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsClasse(@PathVariable("id") Integer id) throws NotExistsException {
        List<EtudiantDTO> etudiants = classeService.getEtudiantsClasse(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    // link etudiants to classe
    @PostMapping(path="/{id}/etudiants")
    public ResponseEntity<String> linkEtudiants(@PathVariable("id") Integer id, @RequestBody List<Integer> etudiants) throws NotExistsException, AlreadyExistsException {
        classeService.linkEtudiants(id, etudiants);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    //unlink etudiants from classe
    @DeleteMapping(path="/{id}/etudiants")
    public ResponseEntity<String> unlinkEtudiants(@PathVariable("id") Integer id, @RequestBody List<Integer> etudiants) throws NotExistsException, AlreadyExistsException {
        classeService.linkEtudiants(id, etudiants);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

}
