package com.example.back.controllers;

import com.example.back.DTO.EtudiantDTO;
import com.example.back.DTO.MatiereDTO;
import com.example.back.DTO.ProfesseurDTO;
import com.example.back.entities.Matiere;
import com.example.back.exceptions.AlreadyExistsException;
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
    public ResponseEntity<MatiereDTO> addMatiere(@RequestBody Matiere matiere) throws Exception {
        MatiereDTO matiereDTO = matiereService.create(matiere);
        return new ResponseEntity<>(matiereDTO, HttpStatus.CREATED);
    }

    // update

    @PutMapping(path = "")
    public ResponseEntity<String> updateMatiere(@RequestBody Matiere matiere) throws Exception {
        matiereService.update(matiere);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    // get

    @GetMapping(path = "")
    public ResponseEntity<List<MatiereDTO>> getMatiere() {
        List<MatiereDTO> matieres = matiereService.readAll();
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Matiere> getMatiere(@PathVariable("id") Integer id) throws Exception {
        Matiere matiere = matiereService.read(id);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }

    //get by nom

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<List<MatiereDTO>> getMatiere(@PathVariable("nom") String nom) {
        List<MatiereDTO> matieres = matiereService.searchByNom(nom);
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
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsMatiere(@PathVariable("id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(matiereService.getEtudiantsMatiere(id), HttpStatus.OK);
    }

    // link etudiants to matiere
    @PostMapping(path = "/{id}/etudiants")
    public ResponseEntity<String> linkEtudiants(@PathVariable("id") Integer id, @RequestBody List<Integer> etudiants) throws AlreadyExistsException, NotExistsException {
        matiereService.linkEtudiants(id, etudiants);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // unlink etudiants from matiere
    @DeleteMapping(path = "/{id}/etudiants")
    public ResponseEntity<String> unlinkEtudiants(@PathVariable("id") Integer id, @RequestBody List<Integer> etudiants) throws AlreadyExistsException, NotExistsException {
        matiereService.unlinkEtudiants(id, etudiants);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/professeurs")
    public ResponseEntity<List<ProfesseurDTO>> getProfesseursMatiere(@PathVariable("id") Integer id) throws NotExistsException {
        return new ResponseEntity<>(matiereService.getProfesseursMatiere(id), HttpStatus.OK);
    }

    // link professeurs to matiere
    @PostMapping(path = "/{id}/professeurs")
    public ResponseEntity<String> linkProfesseurs(@PathVariable("id") Integer id, @RequestBody List<Integer> professeurs) throws AlreadyExistsException, NotExistsException {
        matiereService.linkProfesseurs(id, professeurs);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // unlink professeurs to matiere
    @DeleteMapping(path = "/{id}/professeurs")
    public ResponseEntity<String> unlinkProfesseurs(@PathVariable("id") Integer id, @RequestBody List<Integer> professeurs) throws AlreadyExistsException, NotExistsException {
        matiereService.unlinkProfesseurs(id, professeurs);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
