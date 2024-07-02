package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.repository.*;
import com.example.back.services.ClasseService;
import com.example.back.services.Impl.ClasseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/classe")
public class ClasseController {

    @Autowired
    private ClasseServiceImpl classeServiceImpl;


    // insert
    @PutMapping(path = "/")
    public @ResponseBody ResponseEntity<String> addClasse(@RequestBody Classe classe){
        return classeServiceImpl.create(classe) ? new ResponseEntity<String>("Successful", HttpStatus.CREATED) : new ResponseEntity<String>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<String> updateClasse(@RequestBody Classe classe){
        return classeServiceImpl.update(classe) ? new ResponseEntity<String>("Successful", HttpStatus.OK) : new ResponseEntity<String>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<Iterable<ClasseDTO>> getClasse(){
        Iterable<ClasseDTO> classes = classeServiceImpl.readAll();
        return classes != null ? new ResponseEntity<Iterable<ClasseDTO>>(classes, HttpStatus.OK) : new ResponseEntity<Iterable<ClasseDTO>>((Iterable<ClasseDTO>) null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<ClasseDTO> getClasse(@PathVariable("id") Integer id){
        ClasseDTO classe = classeServiceImpl.read(id);
        return classe != null ? new ResponseEntity<ClasseDTO>(classe, HttpStatus.OK) : new ResponseEntity<ClasseDTO>((ClasseDTO) null, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<ClasseDTO>> getClasse(@PathVariable("nom") String nom){
        Iterable<ClasseDTO> classes = classeServiceImpl.searchByNom(nom);
        return classes != null ? new ResponseEntity<Iterable<ClasseDTO>>(classes, HttpStatus.OK) : new ResponseEntity<Iterable<ClasseDTO>>((Iterable<ClasseDTO>) null, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteClasse(@PathVariable("id") Integer id){
        return classeServiceImpl.delete(id) ? new ResponseEntity<String>("deleted", HttpStatus.OK) : new ResponseEntity<String>("error", HttpStatus.CONFLICT);
    }

}
