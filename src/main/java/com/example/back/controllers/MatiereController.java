package com.example.back.controllers;

import com.example.back.DTO.MatiereDTO;
import com.example.back.entities.Matiere;
import com.example.back.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin()
@RequestMapping(path = "/api/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;


    // insert
    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addMatiere(@RequestBody Matiere matiere) throws Exception {
        matiereService.create(matiere);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateMatiere(@RequestBody Matiere matiere) throws Exception {
        matiereService.update(matiere);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<MatiereDTO>> getMatiere(){
        List<MatiereDTO> matieres = matiereService.readAll();
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<MatiereDTO> getMatiere(@PathVariable("id") Integer id) throws Exception {
        MatiereDTO matiere = matiereService.read(id);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<List<MatiereDTO>> getMatiere(@PathVariable("nom") String nom){
        List<MatiereDTO> matieres = matiereService.searchByNom(nom);
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteMatiere(@PathVariable("id") Integer id) throws Exception {
        matiereService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
