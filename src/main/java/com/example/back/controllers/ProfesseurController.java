package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.Impl.ProfesseurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@CrossOrigin()
@RequestMapping(path = "/api/professeur")
public class ProfesseurController {

    @Autowired
    private ProfesseurServiceImpl professeurServiceImpl;

    // login
    @PostMapping(path = "/login")
    public @ResponseBody ResponseEntity<ProfesseurDTO> login(@RequestBody ProfesseurDTO professeur) {
        ProfesseurDTO found = professeurServiceImpl.getByEmail(professeur.getEmail());
        if (found == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(!Objects.equals(found.getMotDePasse(), professeur.getMotDePasse())) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }


    // insert
    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addProfesseur(@RequestBody Professeur professeur){
        return professeurServiceImpl.create(professeur) ? new ResponseEntity<>("Successful", HttpStatus.CREATED) : new ResponseEntity<>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateProfesseur(@RequestBody Professeur professeur){
        return professeurServiceImpl.update(professeur) ? new ResponseEntity<>("Successful", HttpStatus.OK) : new ResponseEntity<>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<Iterable<ProfesseurDTO>> getProfesseur(){
        Iterable<ProfesseurDTO> professeurs = professeurServiceImpl.readAll();
        return professeurs != null ? new ResponseEntity<>(professeurs, HttpStatus.OK) : new ResponseEntity<>(professeurs, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable("id") Integer id){
        ProfesseurDTO professeur = professeurServiceImpl.read(id);
        return professeur != null ? new ResponseEntity<>(professeur, HttpStatus.OK) : new ResponseEntity<>(professeur, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<ProfesseurDTO>> getProfesseur(@PathVariable("nom") String nom){
        Iterable<ProfesseurDTO> professeurs = professeurServiceImpl.searchByNom(nom);
        return professeurs != null ? new ResponseEntity<>(professeurs, HttpStatus.OK) : new ResponseEntity<>(professeurs, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteProfesseur(@PathVariable("id") Integer id){
        return professeurServiceImpl.delete(id) ? new ResponseEntity<>("deleted", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.CONFLICT);
    }

    //autre

    @GetMapping(path = "/etudiant/{id}")
    public @ResponseBody ResponseEntity<Iterable<ProfesseurDTO>> findProfesseursByEtudiantid(@PathVariable("id") Integer id){
        Iterable<ProfesseurDTO> professeurs = professeurServiceImpl.getByEtudiantId(id);
        return professeurs != null ? new ResponseEntity<>(professeurs, HttpStatus.OK) : new ResponseEntity<>(professeurs, HttpStatus.NOT_FOUND);
    }

}
