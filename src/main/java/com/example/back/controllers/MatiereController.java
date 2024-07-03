package com.example.back.controllers;

import com.example.back.DTO.*;
import com.example.back.entities.*;
import com.example.back.services.Impl.MatiereServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin()
@RequestMapping(path = "/api/matiere")
public class MatiereController {

    @Autowired
    private MatiereServiceImpl matiereServiceImpl;


    // insert
    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addMatiere(@RequestBody Matiere matiere){
        return matiereServiceImpl.create(matiere) ? new ResponseEntity<>("Successful", HttpStatus.CREATED) : new ResponseEntity<>("Error in creation", HttpStatus.CONFLICT);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateMatiere(@RequestBody Matiere matiere){
        return matiereServiceImpl.update(matiere) ? new ResponseEntity<>("Successful", HttpStatus.OK) : new ResponseEntity<>("Error in update", HttpStatus.CONFLICT);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<Iterable<MatiereDTO>> getMatiere(){
        Iterable<MatiereDTO> matieres = matiereServiceImpl.readAll();
        return matieres != null ? new ResponseEntity<>(matieres, HttpStatus.OK) : new ResponseEntity<>(matieres, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<MatiereDTO> getMatiere(@PathVariable("id") Integer id){
        MatiereDTO matiere = matiereServiceImpl.read(id);
        return matiere != null ? new ResponseEntity<>(matiere, HttpStatus.OK) : new ResponseEntity<>((MatiereDTO) null, HttpStatus.NOT_FOUND);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<Iterable<MatiereDTO>> getMatiere(@PathVariable("nom") String nom){
        Iterable<MatiereDTO> matieres = matiereServiceImpl.searchByNom(nom);
        return matieres != null ? new ResponseEntity<>(matieres, HttpStatus.OK) : new ResponseEntity<>((Iterable<MatiereDTO>) null, HttpStatus.NOT_FOUND);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteMatiere(@PathVariable("id") Integer id){
        return matiereServiceImpl.delete(id) ? new ResponseEntity<>("deleted", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.CONFLICT);
    }
}
