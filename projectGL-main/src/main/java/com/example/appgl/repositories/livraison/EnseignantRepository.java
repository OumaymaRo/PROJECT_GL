package com.example.appgl.repositories.livraison;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appgl.models.enseignant;
import com.example.appgl.models.imprimante;

public interface EnseignantRepository extends JpaRepository<enseignant, Integer> {

}
