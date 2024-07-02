package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.Impl.ProfesseurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/api/professeur")
public class ProfesseurController {

    @Autowired
    private ProfesseurServiceImpl professeurServiceImpl;


    // insert
    @PutMapping(path = "/")
    public @ResponseBody ResponseEntity<String> addProfesseur(@RequestBody Professeur professeur){
        return professeurServiceImpl.create(professeur) ? new ResponseEntity<String>("Successful", HttpStatus.CREATED) : new ResponseEntity<String>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<String> updateProfesseur(@RequestBody Professeur professeur){
        return professeurServiceImpl.update(professeur) ? new ResponseEntity<String>("Successful", HttpStatus.OK) : new ResponseEntity<String>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<Iterable<ProfesseurDTO>> getProfesseur(){
        Iterable<ProfesseurDTO> professeurs = professeurServiceImpl.readAll();
        return professeurs != null ? new ResponseEntity<Iterable<ProfesseurDTO>>(professeurs, HttpStatus.OK) : new ResponseEntity<Iterable<ProfesseurDTO>>((Iterable<ProfesseurDTO>) null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable("id") Integer id){
        ProfesseurDTO professeur = professeurServiceImpl.read(id);
        return professeur != null ? new ResponseEntity<ProfesseurDTO>(professeur, HttpStatus.OK) : new ResponseEntity<ProfesseurDTO>((ProfesseurDTO) null, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<ProfesseurDTO>> getProfesseur(@PathVariable("nom") String nom){
        Iterable<ProfesseurDTO> professeurs = professeurServiceImpl.searchByNom(nom);
        return professeurs != null ? new ResponseEntity<Iterable<ProfesseurDTO>>(professeurs, HttpStatus.OK) : new ResponseEntity<Iterable<ProfesseurDTO>>((Iterable<ProfesseurDTO>) null, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteProfesseur(@PathVariable("id") Integer id){
        return professeurServiceImpl.delete(id) ? new ResponseEntity<String>("deleted", HttpStatus.OK) : new ResponseEntity<String>("error", HttpStatus.CONFLICT);
    }

    //autre

    @GetMapping(path = "/etudiant/{id}")
    public @ResponseBody ResponseEntity<Iterable<ProfesseurDTO>> findProfesseursByEtudiantid(@PathVariable("id") Integer id){
        Iterable<ProfesseurDTO> professeurs = professeurServiceImpl.getByEtudiantId(id);
        return professeurs != null ? new ResponseEntity<Iterable<ProfesseurDTO>>(professeurs, HttpStatus.OK) : new ResponseEntity<Iterable<ProfesseurDTO>>(professeurs, HttpStatus.NOT_FOUND);
    }

}
