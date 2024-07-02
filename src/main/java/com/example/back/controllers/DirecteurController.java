package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.Impl.DirecteurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/directeur")
public class DirecteurController {

    @Autowired
    private DirecteurServiceImpl directeurServiceImpl;


    // insert
    @PutMapping(path = "/")
    public @ResponseBody ResponseEntity<String> addDirecteur(@RequestBody Directeur directeur){
        return directeurServiceImpl.create(directeur) ? new ResponseEntity<String>("Successful", HttpStatus.CREATED) : new ResponseEntity<String>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<String> updateDirecteur(@RequestBody Directeur directeur){
        return directeurServiceImpl.update(directeur) ? new ResponseEntity<String>("Successful", HttpStatus.OK) : new ResponseEntity<String>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<Iterable<DirecteurDTO>> getDirecteur(){
        Iterable<DirecteurDTO> directeurs = directeurServiceImpl.readAll();
        return directeurs != null ? new ResponseEntity<Iterable<DirecteurDTO>>(directeurs, HttpStatus.OK) : new ResponseEntity<Iterable<DirecteurDTO>>((Iterable<DirecteurDTO>) null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<DirecteurDTO> getDirecteur(@PathVariable("id") Integer id){
        DirecteurDTO directeur = directeurServiceImpl.read(id);
        return directeur != null ? new ResponseEntity<DirecteurDTO>(directeur, HttpStatus.OK) : new ResponseEntity<DirecteurDTO>((DirecteurDTO) null, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<DirecteurDTO>> getDirecteur(@PathVariable("nom") String nom){
        Iterable<DirecteurDTO> directeurs = directeurServiceImpl.searchByNom(nom);
        return directeurs != null ? new ResponseEntity<Iterable<DirecteurDTO>>(directeurs, HttpStatus.OK) : new ResponseEntity<Iterable<DirecteurDTO>>((Iterable<DirecteurDTO>) null, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteDirecteur(@PathVariable("id") Integer id){
        return directeurServiceImpl.delete(id) ? new ResponseEntity<String>("deleted", HttpStatus.OK) : new ResponseEntity<String>("error", HttpStatus.CONFLICT);
    }
}
