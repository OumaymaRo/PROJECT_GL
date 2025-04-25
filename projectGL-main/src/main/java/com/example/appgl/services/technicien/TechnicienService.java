package com.example.appgl.services.technicien;

import com.example.appgl.models.technicien;
import com.example.appgl.repositories.TechnicienRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    public List<technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    public technicien getTechnicienById(Long id) {
        return technicienRepository.findById(id).orElse(null);
    }

    public technicien getTechnicienByUsername(String username) {
        return technicienRepository.findByUsername(username);
    }

    public technicien saveTechnicien(technicien technicien) {
        return technicienRepository.save(technicien);
    }
} 