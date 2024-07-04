package com.example.back.controllers;

import com.example.back.DTO.EtudiantDTO;
import com.example.back.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin()
@RequestMapping(path = "/api/etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;


    // login
    @PostMapping(path = "/login")
    public @ResponseBody ResponseEntity<EtudiantDTO> login(@RequestBody EtudiantDTO etudiant) throws Exception {
        EtudiantDTO found = etudiantService.getByEmail(etudiant.getEmail());
        if (found == null) {
            throw new Exception("not found");
        }
        if(!Objects.equals(found.getMotDePasse(), etudiant.getMotDePasse())) {
            throw new Exception("wrong password");
        }
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    // insert
    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addEtudiant(@RequestBody EtudiantDTO etudiant) throws Exception {
        etudiantService.create(etudiant);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public @ResponseBody ResponseEntity<String> updateEtudiant(@RequestBody EtudiantDTO etudiant) throws Exception {
        etudiantService.update(etudiant);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<List<EtudiantDTO>> getEtudiant(){
        List<EtudiantDTO> etudiants = etudiantService.readAll();
        return etudiants != null ? new ResponseEntity<>(etudiants, HttpStatus.OK) : new ResponseEntity<>((List<EtudiantDTO>) null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody ResponseEntity<EtudiantDTO> getEtudiant(@PathVariable("id") Integer id) throws Exception {
        EtudiantDTO etudiant = etudiantService.read(id);
        return new ResponseEntity<>(etudiant, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public @ResponseBody ResponseEntity<List<EtudiantDTO>> getEtudiant(@PathVariable("nom") String nom){
        List<EtudiantDTO> etudiants = etudiantService.searchByNom(nom);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    //delete

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteEtudiant(@PathVariable("id") Integer id) throws Exception {
        etudiantService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    //autre

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<List<EtudiantDTO>> findEtudiantsByClasseid(@PathVariable("id") Integer id) throws Exception {
        List<EtudiantDTO> etudiants = etudiantService.getByClasseId(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @GetMapping(path = "/professeur/{id}")
    public @ResponseBody ResponseEntity<List<EtudiantDTO>> findEtudiantsByProfesseurid(@PathVariable("id") Integer id) throws Exception {
        List<EtudiantDTO> etudiants = etudiantService.getByProfesseurId(id);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

}
