package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.Impl.EtudiantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantServiceImpl etudiantServiceImpl;


    // insert
    @PutMapping(path = "/")
    public @ResponseBody ResponseEntity<String> addEtudiant(@RequestBody Etudiant etudiant){
        return etudiantServiceImpl.create(etudiant) ? new ResponseEntity<>("Successful", HttpStatus.CREATED) : new ResponseEntity<>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<String> updateEtudiant(@RequestBody Etudiant etudiant){
        return etudiantServiceImpl.update(etudiant) ? new ResponseEntity<>("Successful", HttpStatus.OK) : new ResponseEntity<>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<Iterable<EtudiantDTO>> getEtudiant(){
        Iterable<EtudiantDTO> etudiants = etudiantServiceImpl.readAll();
        return etudiants != null ? new ResponseEntity<Iterable<EtudiantDTO>>(etudiants, HttpStatus.OK) : new ResponseEntity<Iterable<EtudiantDTO>>((Iterable<EtudiantDTO>) null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<EtudiantDTO> getEtudiant(@PathVariable("id") Integer id){
        EtudiantDTO etudiant = etudiantServiceImpl.read(id);
        return etudiant != null ? new ResponseEntity<EtudiantDTO>(etudiant, HttpStatus.OK) : new ResponseEntity<EtudiantDTO>((EtudiantDTO) null, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<EtudiantDTO>> getEtudiant(@PathVariable("nom") String nom){
        Iterable<EtudiantDTO> etudiants = etudiantServiceImpl.searchByNom(nom);
        return etudiants != null ? new ResponseEntity<Iterable<EtudiantDTO>>(etudiants, HttpStatus.OK) : new ResponseEntity<Iterable<EtudiantDTO>>((Iterable<EtudiantDTO>) null, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteEtudiant(@PathVariable("id") Integer id){
        return etudiantServiceImpl.delete(id) ? new ResponseEntity<String>("deleted", HttpStatus.OK) : new ResponseEntity<String>("error", HttpStatus.CONFLICT);
    }
    //autre

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<Iterable<EtudiantDTO>> findEtudiantsByClasseid(@PathVariable("id") Integer id){
        Iterable<EtudiantDTO> etudiants = etudiantServiceImpl.getByClasseId(id);
        return etudiants != null ? new ResponseEntity<Iterable<EtudiantDTO>>(etudiants, HttpStatus.OK) : new ResponseEntity<Iterable<EtudiantDTO>>(etudiants, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/professeur/{id}")
    public @ResponseBody ResponseEntity<Iterable<EtudiantDTO>> findEtudiantsByProfesseurid(@PathVariable("id") Integer id){
        Iterable<EtudiantDTO> etudiants = etudiantServiceImpl.getByProfesseurId(id);
        return etudiants != null ? new ResponseEntity<Iterable<EtudiantDTO>>(etudiants, HttpStatus.OK) : new ResponseEntity<Iterable<EtudiantDTO>>(etudiants, HttpStatus.NOT_FOUND);
    }

}
