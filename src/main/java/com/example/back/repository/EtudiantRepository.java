package com.example.back.repository;

import com.example.back.DTO.EtudiantDTO;
import com.example.back.entities.Etudiant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> {

    EtudiantDTO findEtudiantByEmail(String email);

    Iterable<EtudiantDTO> findBy();

    Iterable<EtudiantDTO> searchEtudiantsByNom(String nom);

    Iterable<EtudiantDTO> findEtudiantsByClasse_Id(Integer classeId);

    @Query("SELECT e FROM Etudiant e " +
           "JOIN e.classe c " +
           "JOIN c.professeurs p " +
           "WHERE p.id = :id")
    Iterable<EtudiantDTO> getEtudiantsByProfesseurid(@Param("id") Integer id);
}
