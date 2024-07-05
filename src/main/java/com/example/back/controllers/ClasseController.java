package com.example.back.controllers;

import com.example.back.DTO.ClasseDTO;
import com.example.back.entities.Classe;
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
    public  ResponseEntity<ClasseDTO> addClasse(@RequestBody Classe classe) throws Exception {
        ClasseDTO classe1 = classeService.create(classe);
        return new ResponseEntity<>(classe1, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public  ResponseEntity<String> updateClasse(@RequestBody Classe classe) throws Exception {
        classeService.update(classe);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public  ResponseEntity<List<ClasseDTO>> getClasse(){
        List<ClasseDTO> classes = classeService.readAll();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public  ResponseEntity<Classe> getClasse(@PathVariable("id") Integer id) throws Exception {
        Classe classe = classeService.read(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public  ResponseEntity<List<ClasseDTO>> getClasse(@PathVariable("nom") String nom){
        List<ClasseDTO> classes = classeService.searchByNom(nom);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<String> deleteClasse(@PathVariable("id") Integer id) throws Exception {
        classeService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

}
