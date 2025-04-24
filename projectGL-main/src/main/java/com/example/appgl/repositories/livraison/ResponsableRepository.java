package com.example.appgl.repositories.livraison;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appgl.models.enseignant;
import com.example.appgl.models.imprimante;
import com.example.appgl.models.responsable;

public interface ResponsableRepository extends JpaRepository<responsable, Integer>  {

    Optional<enseignant> findResponsableById(Long id);

}
