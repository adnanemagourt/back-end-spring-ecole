package com.example.back.controllers;

import com.example.back.DTO.etudiantDTO.UnlinkedEtudiantDTO;
import com.example.back.DTO.matiereDTO.LinkedMatiereDTO;
import com.example.back.DTO.matiereDTO.MatiereDTO;
import com.example.back.DTO.matiereDTO.UnlinkedMatiereDTO;
import com.example.back.DTO.professeurDTO.UnlinkedProfesseurDTO;
import com.example.back.exceptions.NotExistsException;
import com.example.back.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;


    // insert
    @PostMapping(path = "")
    public ResponseEntity<MatiereDTO> addMatiere(@RequestBody UnlinkedMatiereDTO matiere) throws Exception {
        MatiereDTO matiereDTO = matiereService.create(matiere);
        return new ResponseEntity<>(matiereDTO, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateMatiere(@RequestBody UnlinkedMatiereDTO matiere) throws Exception {
        matiereService.update(matiere);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<UnlinkedMatiereDTO>> getMatiere() {
        List<UnlinkedMatiereDTO> matieres = matiereService.readAll();
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<LinkedMatiereDTO> getMatiere(@PathVariable("id") Integer id) throws Exception {
        LinkedMatiereDTO matiere = matiereService.read(id);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<UnlinkedMatiereDTO>> getMatiere(@PathVariable("nom") String nom) {
        List<UnlinkedMatiereDTO> matieres = matiereService.searchByNom(nom);
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMatiere(@PathVariable("id") Integer id) throws Exception {
        matiereService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    // get etudiants of matiere
    @GetMapping(path = "/{id}/etudiants")
    public ResponseEntity<List<UnlinkedEtudiantDTO>> getEtudiantsMatiere(@PathVariable("id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(matiereService.getEtudiantsMatiere(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/professeurs")
    public ResponseEntity<List<UnlinkedProfesseurDTO>> getProfesseursMatiere(@PathVariable("id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(matiereService.getProfesseursMatiere(id), HttpStatus.OK);
    }

}
