package com.example.back.controllers;

import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Classe;
import com.example.back.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@CrossOrigin()
@RequestMapping(path = "/api/professeur")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    // login
    @PostMapping(path = "/login")
    public  ResponseEntity<ProfesseurDTO> login(@RequestBody ProfesseurDTO professeur) throws Exception {
        ProfesseurDTO found = professeurService.getByEmail(professeur.getEmail());
        if (found == null) {
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            throw new Exception("not found");
        }
        if(!Objects.equals(found.getMotDePasse(), professeur.getMotDePasse())) {
            //return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            throw new Exception("wrong password");
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }


    // insert
    @PostMapping(path = "")
    public  ResponseEntity<ProfesseurDTO> addProfesseur(@RequestBody ProfesseurDTO professeur) throws Exception {
        professeurService.create(professeur);
        return new ResponseEntity<>(professeur, HttpStatus.CREATED);
        }

    // update

    @PutMapping(path = "")
    public  ResponseEntity<String> updateProfesseur(@RequestBody ProfesseurDTO professeur) throws Exception {
        professeurService.update(professeur);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
        }

    // get

    @GetMapping(path = "")
    public  ResponseEntity<List<ProfesseurDTO>> getProfesseur(){
        List<ProfesseurDTO> professeurs = professeurService.readAll();
        return new ResponseEntity<>(professeurs, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public  ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable("id") Integer id) throws Exception {
        ProfesseurDTO professeur = professeurService.read(id);
        return new ResponseEntity<>(professeur, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public  ResponseEntity<List<ProfesseurDTO>> getProfesseur(@PathVariable("nom") String nom){
        List<ProfesseurDTO> professeurs = professeurService.searchByNom(nom);
        return new ResponseEntity<>(professeurs, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<String> deleteProfesseur(@PathVariable("id") Integer id) throws Exception {
        professeurService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    //autre

    @GetMapping(path = "/etudiant/{id}")
    public  ResponseEntity<List<ProfesseurDTO>> findProfesseursByEtudiantid(@PathVariable("id") Integer id) throws Exception {
        List<ProfesseurDTO> professeurs = professeurService.getByEtudiantId(id);
        return new ResponseEntity<>(professeurs, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/classes")
    public  ResponseEntity<List<Classe>> findProfesseurClasses(@PathVariable("id") Integer id) throws Exception {
        List<Classe> classes = professeurService.findProfesseurClasses(id);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

}
