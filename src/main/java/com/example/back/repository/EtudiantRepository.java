package com.example.back.repository;

import com.example.back.entities.Etudiant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> {
    Etudiant findEtudiantByEmail(String email);

    void updateById(Integer id, Etudiant etudiant);

    Iterable<Etudiant> searchEtudiantsByNom(String nom);

    Iterable<Etudiant> findEtudiantsByClasse_Id(Integer classeId);

    @Query("SELECT e FROM Etudiant e " +
           "JOIN e.classe c " +
           "JOIN c.professeurs p " +
           "WHERE p.id = :id")
    Iterable<Etudiant> getEtudiantsByProfesseurid(@Param("id") Integer id);
}
