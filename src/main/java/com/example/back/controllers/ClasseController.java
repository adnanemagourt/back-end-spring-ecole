package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin()
@RequestMapping(path = "/api/classe")
public class ClasseController {

    @Autowired
    private ClasseService classeService;


    // insert
    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addClasse(@RequestBody Classe classe){
        return classeService.create(classe) ? new ResponseEntity<>("Successful", HttpStatus.CREATED) : new ResponseEntity<>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateClasse(@RequestBody Classe classe){
        return classeService.update(classe) ? new ResponseEntity<>("Successful", HttpStatus.OK) : new ResponseEntity<>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<ClasseDTO>> getClasse(){
        List<ClasseDTO> classes = classeService.readAll();
        return classes != null ? new ResponseEntity<>(classes, HttpStatus.OK) : new ResponseEntity<>(classes, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<ClasseDTO> getClasse(@PathVariable("id") Integer id){
        ClasseDTO classe = classeService.read(id);
        return classe != null ? new ResponseEntity<>(classe, HttpStatus.OK) : new ResponseEntity<>( classe, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<ClasseDTO>> getClasse(@PathVariable("nom") String nom){
        Iterable<ClasseDTO> classes = classeService.searchByNom(nom);
        return classes != null ? new ResponseEntity<>(classes, HttpStatus.OK) : new ResponseEntity<>(classes, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteClasse(@PathVariable("id") Integer id){
        return classeService.delete(id) ? new ResponseEntity<>("deleted", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.CONFLICT);
    }

}
