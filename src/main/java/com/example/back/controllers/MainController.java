package com.example.back.controllers;

import com.example.back.DTO.CustomResponseRequest;
import com.example.back.entities.*;
import com.example.back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private DirecteurRepository directeurRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;


    @PostMapping(path = "/login")
    public @ResponseBody CustomResponseRequest.Response login(@RequestBody CustomResponseRequest.LoginRequestBody body){
        Personne result;
        switch (body.type){
            case "professeur":
                result = professeurRepository.findProfesseurByEmail(body.email);
                if(result == null){ return new CustomResponseRequest.ResponseError(400, "User not found");}
                if(Objects.equals(result.getMotDePasse(), body.password)){
                    return new CustomResponseRequest.ResponsePersonne(200, result);
                }
                break;
            case "directeur":
                result = directeurRepository.findDirecteurByEmail(body.email);
                if(result == null){ return new CustomResponseRequest.ResponseError(400, "User not found");}
                if(Objects.equals(result.getMotDePasse(), body.password)){
                    return new CustomResponseRequest.ResponsePersonne(200, result);
                }
                break;
            case "etudiant":
                result = etudiantRepository.findEtudiantByEmail(body.email);
                if(result == null){ return new CustomResponseRequest.ResponseError(400, "User not found");}
                if(Objects.equals(result.getMotDePasse(), body.password)){
                    return new CustomResponseRequest.ResponsePersonne(200, result);
                }
                break;
            default:
                return new CustomResponseRequest.ResponseError(400, "User type non-existent");
        }
        return new CustomResponseRequest.ResponseError(400, "Wrong password");

    }

    // insert

    @PutMapping(path = "/etudiant")
    public @ResponseBody CustomResponseRequest.Response addEtudiant(@RequestBody Etudiant etudiant){
        Etudiant already_existing = etudiantRepository.findEtudiantByEmail(etudiant.getEmail());
        if(already_existing != null) {
            return new CustomResponseRequest.ResponseError(400, "Email already exists");
        }
        etudiantRepository.save(etudiant);
        return new CustomResponseRequest.Response(200);

    }

    @PutMapping(path = "/professeur")
    public @ResponseBody CustomResponseRequest.Response addProfesseur(@RequestBody Professeur professeur){
        Professeur already_existing = professeurRepository.findProfesseurByEmail(professeur.getEmail());
        if(already_existing != null) {
            return new CustomResponseRequest.ResponseError(400, "Email already exists");
        }
        professeurRepository.save(professeur);
        return new CustomResponseRequest.Response(200);
    }

    @PutMapping(path = "/directeur")
    public @ResponseBody CustomResponseRequest.Response addDirecteur(@RequestBody Directeur directeur){
        Directeur already_existing = directeurRepository.findDirecteurByEmail(directeur.getEmail());
        if(already_existing != null) {
            return new CustomResponseRequest.ResponseError(400, "Email already exists");
        }
        directeurRepository.save(directeur);
        return new CustomResponseRequest.Response(200);
    }

    @PutMapping(path = "/classe")
    public @ResponseBody CustomResponseRequest.Response addClasse(@RequestBody Classe classe){
        classeRepository.save(classe);
        return new CustomResponseRequest.Response(200);
    }

    @PutMapping(path = "/matiere")
    public @ResponseBody CustomResponseRequest.Response addMatiere(@RequestBody Matiere matiere){
        matiereRepository.save(matiere);
        return new CustomResponseRequest.Response(200);
    }


    // update

    @PostMapping(path = "/etudiant")
    public @ResponseBody CustomResponseRequest.Response updateEtudiant(@RequestBody Etudiant etudiant){
        etudiantRepository.updateById(etudiant.getId(), etudiant);
        return new CustomResponseRequest.Response(200);
    }

    @PostMapping(path = "/professeur")
    public @ResponseBody CustomResponseRequest.Response updateProfesseur(@RequestBody Professeur professeur){
        professeurRepository.updateById(professeur.getId(), professeur);
        return new CustomResponseRequest.Response(200);
    }

    @PostMapping(path = "/directeur")
    public @ResponseBody CustomResponseRequest.Response updateDirecteur(@RequestBody Directeur directeur){
        directeurRepository.updateById(directeur.getId(), directeur);
        return new CustomResponseRequest.Response(200);
    }

    @PostMapping(path = "/classe")
    public @ResponseBody CustomResponseRequest.Response updateClasse(@RequestBody Classe classe){
        classeRepository.updateById(classe.getId(), classe);
        return new CustomResponseRequest.Response(200);
    }

    @PostMapping(path = "/matiere")
    public @ResponseBody CustomResponseRequest.Response updateMatiere(@RequestBody Matiere matiere){
        matiereRepository.updateById(matiere.getId(), matiere);
        return new CustomResponseRequest.Response(200);
    }

    // get

    @GetMapping(path = "/etudiant")
    public @ResponseBody CustomResponseRequest.Response getEtudiant(){
        Iterable<Etudiant> etudiants = etudiantRepository.findAll();
        return new CustomResponseRequest.ResponseList(200, etudiants);
    }

    @GetMapping(path = "/directeur")
    public @ResponseBody CustomResponseRequest.Response getDirecteur(){
        Iterable<Directeur> directeurs = directeurRepository.findAll();
        return new CustomResponseRequest.ResponseList(200, directeurs);
    }

    @GetMapping(path = "/professeur")
    public @ResponseBody CustomResponseRequest.Response getProfesseur(){
        Iterable<Professeur> professeurs = professeurRepository.findAll();
        return new CustomResponseRequest.ResponseList(200, professeurs);
    }

    @GetMapping(path = "/classe")
    public @ResponseBody CustomResponseRequest.Response getClasse(){
        Iterable<Classe> classes = classeRepository.findAll();
        return new CustomResponseRequest.ResponseListClasse(200, classes);
    }

    @GetMapping(path = "/matiere")
    public @ResponseBody CustomResponseRequest.Response getMatiere(){
        Iterable<Matiere> matieres = matiereRepository.findAll();
        return new CustomResponseRequest.ResponseList(200, matieres);
    }

    @GetMapping(path = "/etudiant/id/{id}")
    public @ResponseBody CustomResponseRequest.Response getEtudiant(@PathVariable("id") Integer id){
        Optional<Etudiant> t = etudiantRepository.findById(id);
        if(t.isPresent()) {
            return new CustomResponseRequest.ResponsePersonne(200, t.get());
        }
        return new CustomResponseRequest.ResponseError(400, "Etudiant not found");

    }

    @GetMapping(path = "/professeur/id/{id}")
    public @ResponseBody CustomResponseRequest.Response getProfesseur(@PathVariable("id") Integer id){
        Optional<Professeur> t = professeurRepository.findById(id);
        if(t.isPresent()) {
            return new CustomResponseRequest.ResponsePersonne(200, t.get());
        }
        return new CustomResponseRequest.ResponseError(400, "Professeur not found");

    }

    @GetMapping(path = "/directeur/id/{id}")
    public @ResponseBody CustomResponseRequest.Response getDirecteur(@PathVariable("id") Integer id){
        Optional<Directeur> t = directeurRepository.findById(id);
        if(t.isPresent()) {
            return new CustomResponseRequest.ResponsePersonne(200, t.get());
        }
        return new CustomResponseRequest.ResponseError(400, "Directeur not found");

    }

    @GetMapping(path = "/classe/id/{id}")
    public @ResponseBody CustomResponseRequest.Response getClasse(@PathVariable("id") Integer id){
        Optional<Classe> t = classeRepository.findById(id);
        if(t.isPresent()) {
            return new CustomResponseRequest.ResponseClasse(200, t.get());
        }
        return new CustomResponseRequest.ResponseError(400, "Classe not found");

    }

    @GetMapping(path = "/matiere/id/{id}")
    public @ResponseBody CustomResponseRequest.Response getMatiere(@PathVariable("id") Integer id){
        Optional<Matiere> t = matiereRepository.findById(id);
        if(t.isPresent()) {
            return new CustomResponseRequest.ResponseMatiere(200, t.get());
        }
        return new CustomResponseRequest.ResponseError(400, "Matiere not found");

    }

    //get by nom

    @GetMapping(path = "/etudiant/nom/{nom}")
    public @ResponseBody CustomResponseRequest.Response getEtudiant(@PathVariable("nom") String nom){
        Iterable<Etudiant> etudiants = etudiantRepository.searchEtudiantsByNom(nom);
        return new CustomResponseRequest.ResponseList(200, etudiants);
    }

    @GetMapping(path = "/directeur/nom/{nom}")
    public @ResponseBody CustomResponseRequest.Response getDirecteur(@PathVariable("nom") String nom){
        Iterable<Directeur> directeurs = directeurRepository.searchDirecteursByNom(nom);
        return new CustomResponseRequest.ResponseList(200, directeurs);
    }

    @GetMapping(path = "/professeur/nom/{nom}")
    public @ResponseBody CustomResponseRequest.Response getProfesseur(@PathVariable("nom") String nom){
        Iterable<Professeur> professeurs = professeurRepository.searchProfesseursByNom(nom);
        return new CustomResponseRequest.ResponseList(200, professeurs);
    }

    @GetMapping(path = "/classe/nom/{nom}")
    public @ResponseBody CustomResponseRequest.Response getClasse(@PathVariable("nom") String nom){
        Iterable<Classe> classes = classeRepository.searchClassesByNom(nom);
        return new CustomResponseRequest.ResponseListClasse(200, classes);
    }

    @GetMapping(path = "/matiere/nom/{nom}")
    public @ResponseBody CustomResponseRequest.Response getMatiere(@PathVariable("nom") String nom){
        Iterable<Matiere> matieres = matiereRepository.searchMatieresByNom(nom);
        return new CustomResponseRequest.ResponseListMatiere(200, matieres);
    }


    //delete

    @DeleteMapping(path = "/etudiant/{id}")
    public @ResponseBody CustomResponseRequest.Response deleteEtudiant(@PathVariable("id") Integer id){
        etudiantRepository.deleteById(id);
        return new CustomResponseRequest.Response(200);
    }

    @DeleteMapping(path = "/professeur/{id}")
    public @ResponseBody CustomResponseRequest.Response deleteProfesseur(@PathVariable("id") Integer id){
        professeurRepository.deleteById(id);
        return new CustomResponseRequest.Response(200);
    }

    @DeleteMapping(path = "/directeur/{id}")
    public @ResponseBody CustomResponseRequest.Response deleteDirecteur(@PathVariable("id") Integer id){
        directeurRepository.deleteById(id);
        return new CustomResponseRequest.Response(200);
    }

    @DeleteMapping(path = "/classe/{id}")
    public @ResponseBody CustomResponseRequest.Response deleteClasse(@PathVariable("id") Integer id){
        classeRepository.deleteById(id);
        return new CustomResponseRequest.Response(200);
    }

    @DeleteMapping(path = "/matiere/{id}")
    public @ResponseBody CustomResponseRequest.Response deleteMatiere(@PathVariable("id") Integer id){
        matiereRepository.deleteById(id);
        return new CustomResponseRequest.Response(200);
    }


    //autre

    @GetMapping(path = "/etudiant/classe/{id}")
    public @ResponseBody CustomResponseRequest.Response findEtudiantsByClasseid(@PathVariable("id") Integer id){
        Iterable<Etudiant> etudiants = etudiantRepository.findEtudiantsByClasse_Id(id);
        return new CustomResponseRequest.ResponseList(200, etudiants);
    }

    @GetMapping(path = "/professeur/etudiant/{id}")
    public @ResponseBody CustomResponseRequest.Response findProfesseursByEtudiantid(@PathVariable("id") Integer id){
        Optional<Professeur> professeur = professeurRepository.findById(id);
        if(professeur.isPresent()){
            return new CustomResponseRequest.ResponseList(200, professeur.get().getClasses());
        }
        return new CustomResponseRequest.ResponseError(400, "professeur not found");
    }

    @GetMapping(path = "/etudiant/professeur/{id}")
    public @ResponseBody CustomResponseRequest.Response findEtudiantsByProfesseurid(@PathVariable("id") Integer id){
        Iterable<Etudiant> etudiants = etudiantRepository.getEtudiantsByProfesseurid(id);
        return new CustomResponseRequest.ResponseList(200, etudiants);
    }







}
