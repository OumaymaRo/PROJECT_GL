package com.example.appgl.repositories.livraison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appgl.models.imprimante;
@Repository
public interface ImprimanteRepository extends JpaRepository<imprimante, Integer> {
}