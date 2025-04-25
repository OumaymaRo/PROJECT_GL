package com.example.appgl.repositories.livraison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appgl.models.ressource;

@Repository
public interface RessourceRepository extends JpaRepository<ressource, Integer> {
}