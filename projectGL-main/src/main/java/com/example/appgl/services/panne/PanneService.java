package com.example.appgl.services.panne;

import com.example.appgl.models.panne;
import com.example.appgl.repositories.panne.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanneService {

    @Autowired
    private PanneRepository panneRepository;

    public List<panne> getAllPannes() {
        return panneRepository.findAll();
    }

    public panne getPanneById(Integer id) {
        return panneRepository.findById(id).orElse(null);
    }

    public List<panne> getPannesByTechnicien(Long technicienId) {
        return panneRepository.findByTechnicienIdAndEstrepareeFalse(technicienId);
    }

    public panne savePanne(panne panne) {
        return panneRepository.save(panne);
    }

    public void deletePanne(Integer id) {
        panneRepository.deleteById(id);
    }
}

