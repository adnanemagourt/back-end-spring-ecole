package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.Impl.DirecteurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@CrossOrigin()
@RequestMapping(path = "/api/directeur")
public class DirecteurController {

    @Autowired
    private DirecteurServiceImpl directeurServiceImpl;


    // login
    @PostMapping(path = "/login")
    public @ResponseBody ResponseEntity<DirecteurDTO> login(@RequestBody DirecteurDTO directeur) {
        DirecteurDTO found = directeurServiceImpl.getByEmail(directeur.getEmail());
        if (found == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if(!Objects.equals(found.getMotDePasse(), directeur.getMotDePasse())) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    // insert
    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addDirecteur(@RequestBody Directeur directeur) {
        return directeurServiceImpl.create(directeur) ? new ResponseEntity<>("Successful", HttpStatus.CREATED) : new ResponseEntity<>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateDirecteur(@RequestBody Directeur directeur) {
        return directeurServiceImpl.update(directeur) ? new ResponseEntity<>("Successful", HttpStatus.OK) : new ResponseEntity<>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<Iterable<DirecteurDTO>> getDirecteur() {
        Iterable<DirecteurDTO> directeurs = directeurServiceImpl.readAll();
        return directeurs != null ? new ResponseEntity<>(directeurs, HttpStatus.OK) : new ResponseEntity<>(directeurs, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<DirecteurDTO> getDirecteur(@PathVariable("id") Integer id) {
        DirecteurDTO directeur = directeurServiceImpl.read(id);
        return directeur != null ? new ResponseEntity<>(directeur, HttpStatus.OK) : new ResponseEntity<>(directeur, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<DirecteurDTO>> getDirecteur(@PathVariable("nom") String nom) {
        Iterable<DirecteurDTO> directeurs = directeurServiceImpl.searchByNom(nom);
        return directeurs != null ? new ResponseEntity<>(directeurs, HttpStatus.OK) : new ResponseEntity<>(directeurs, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteDirecteur(@PathVariable("id") Integer id) {
        return directeurServiceImpl.delete(id) ? new ResponseEntity<>("deleted", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.CONFLICT);
    }
}
