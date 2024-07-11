package com.example.back.controllers;

import com.example.back.DTO.classeDTO.ClasseDTO;
import com.example.back.DTO.classeDTO.LinkedClasseDTO;
import com.example.back.DTO.classeDTO.UnlinkedClasseDTO;
import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
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
    public ResponseEntity<ClasseDTO> addClasse(@RequestBody UnlinkedClasseDTO classe) throws Exception {
        LinkedClasseDTO classe1 = classeService.create(classe);
        return new ResponseEntity<>(classe1, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateClasse(@RequestBody UnlinkedClasseDTO classe) throws Exception {
        classeService.update(classe);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<UnlinkedClasseDTO>> getClasse() {
        List<UnlinkedClasseDTO> classes = classeService.readAll();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<LinkedClasseDTO> getClasse(@PathVariable("id") Integer id) throws Exception {
        LinkedClasseDTO classe = classeService.read(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<UnlinkedClasseDTO>> getClasse(@PathVariable("nom") String nom) {
        List<UnlinkedClasseDTO> classes = classeService.searchByNom(nom);
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
    public ResponseEntity<List<UnlinkedClasseDTO>> getClassesProfesseur(@PathVariable("id") Integer id) throws NotExistsException {
        List<UnlinkedClasseDTO> classes = classeService.findByProfesseurId(id);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    // get classe of etudiant
    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<LinkedClasseDTO> getClasseOfEtudiant(@PathVariable("id") Integer id) throws NotExistsException {
        LinkedClasseDTO classe = classeService.findByEtudiantId(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    // get professeurs of classe
    @GetMapping(path = "/{id}/professeurs")
    public ResponseEntity<List<UnlinkedProfesseurDTO>> getProfesseursClasse(@PathVariable("id") Integer id) throws NotExistsException {
        List<UnlinkedProfesseurDTO> professeurs = classeService.getProfesseursClasse(id);
        return new ResponseEntity<>(professeurs, HttpStatus.OK);
    }

    //get Etudiants of classe
    @GetMapping(path = "/{id}/etudiants")
    public ResponseEntity<List<UnlinkedEtudiantDTO>> getEtudiantsClasse(@PathVariable("id") Integer id) throws NotExistsException {
        List<UnlinkedEtudiantDTO> etudiants = classeService.getEtudiantsClasse(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }


}
