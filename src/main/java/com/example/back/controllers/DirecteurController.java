package com.example.back.controllers;

import com.example.back.DTO.DirecteurDTO;
import com.example.back.entities.Directeur;
import com.example.back.services.DirecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/directeur")
public class DirecteurController {

    @Autowired
    private DirecteurService directeurService;


    // login
    @PostMapping(path = "/login")
    public ResponseEntity<DirecteurDTO> login(@RequestBody DirecteurDTO directeur) throws Exception {
        DirecteurDTO found = directeurService.getByEmail(directeur.getEmail());
        if (found == null) {
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            throw new Exception("Not found");
        }

        if (!Objects.equals(found.getMotDePasse(), directeur.getMotDePasse())) {
            //return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            throw new Exception("Wrong password");
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    // insert
    @PostMapping(path = "")
    public ResponseEntity<DirecteurDTO> addDirecteur(@RequestBody Directeur directeur) throws Exception {
        DirecteurDTO directeur1 = directeurService.create(directeur);
        return new ResponseEntity<>(directeur1, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateDirecteur(@RequestBody Directeur directeur) throws Exception {
        directeurService.update(directeur);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<DirecteurDTO>> getDirecteur() {
        List<DirecteurDTO> directeurs = directeurService.readAll();
        return new ResponseEntity<>(directeurs, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<DirecteurDTO> getDirecteur(@PathVariable("id") Integer id) throws Exception {
        DirecteurDTO directeur = directeurService.read(id);
        return new ResponseEntity<>(directeur, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<DirecteurDTO>> getDirecteur(@PathVariable("nom") String nom) {
        List<DirecteurDTO> directeurs = directeurService.searchByNom(nom);
        return new ResponseEntity<>(directeurs, HttpStatus.OK);
    }

    //get by nom and prenom

    @GetMapping(path = "/nom/{nom}/prenom/{prenom}")
    public ResponseEntity<DirecteurDTO> getDirecteur(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom) {
        DirecteurDTO directeur = directeurService.getByNomAndPrenom(nom, prenom);
        return new ResponseEntity<>(directeur, HttpStatus.OK);
    }
    //delete

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDirecteur(@PathVariable("id") Integer id) throws Exception {
        directeurService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
