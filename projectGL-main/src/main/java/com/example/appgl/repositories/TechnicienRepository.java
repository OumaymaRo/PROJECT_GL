package com.example.appgl.repositories;

import com.example.appgl.models.technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicienRepository extends JpaRepository<technicien, Long> {
    technicien findByUsername(String username);
} 