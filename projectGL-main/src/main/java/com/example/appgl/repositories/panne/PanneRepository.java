package com.example.appgl.repositories.panne;


import com.example.appgl.models.panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanneRepository extends JpaRepository<panne, Integer> {
    List<panne> findByEstrepareeFalse(); // liste des pannes non réparées
    List<panne> findByTechnicienIdAndEstrepareeFalse(Long technicienId);
}
