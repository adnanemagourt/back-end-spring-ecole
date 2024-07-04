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
    public @ResponseBody ResponseEntity<String> addClasse(@RequestBody Classe classe) throws Exception {
        classeService.create(classe);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateClasse(@RequestBody Classe classe) throws Exception {
        classeService.update(classe);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<ClasseDTO>> getClasse(){
        List<ClasseDTO> classes = classeService.readAll();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<ClasseDTO> getClasse(@PathVariable("id") Integer id) throws Exception {
        ClasseDTO classe = classeService.read(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<List<ClasseDTO>> getClasse(@PathVariable("nom") String nom){
        List<ClasseDTO> classes = classeService.searchByNom(nom);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteClasse(@PathVariable("id") Integer id) throws Exception {
        classeService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

}
