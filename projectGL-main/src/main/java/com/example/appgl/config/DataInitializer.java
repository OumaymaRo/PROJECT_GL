package com.example.appgl.config;

import com.example.appgl.models.Role;
import com.example.appgl.models.User;
import com.example.appgl.repositories.RoleRepository;
import com.example.appgl.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier et créer les rôles s'ils n'existent pas
        createRoleIfNotExists("ROLE_FOURNISSEUR");
        createRoleIfNotExists("ROLE_ENSEIGNANT");
        createRoleIfNotExists("ROLE_TECHNICIEN");
        createRoleIfNotExists("ROLE_RESPONSABLE");
        createRoleIfNotExists("ROLE_CHEF_DEPARTEMENT");

        // Création des utilisateurs de test s'ils n'existent pas
        if (userRepository.count() == 0) {
            // Fournisseur
            User fournisseur = new User();
            fournisseur.setUsername("fournisseur");
            fournisseur.setPassword(passwordEncoder.encode("fournisseur123"));
            fournisseur.setEmail("fournisseur@example.com");
            Set<Role> fournisseurRoles = new HashSet<>();
            fournisseurRoles.add(roleRepository.findByNom("ROLE_FOURNISSEUR").get());
            fournisseur.setRoles(fournisseurRoles);
            userRepository.save(fournisseur);

            // Enseignant
            User enseignant = new User();
            enseignant.setUsername("enseignant");
            enseignant.setPassword(passwordEncoder.encode("enseignant123"));
            enseignant.setEmail("enseignant@example.com");
            Set<Role> enseignantRoles = new HashSet<>();
            enseignantRoles.add(roleRepository.findByNom("ROLE_ENSEIGNANT").get());
            enseignant.setRoles(enseignantRoles);
            userRepository.save(enseignant);

            // Technicien
            User technicien = new User();
            technicien.setUsername("technicien");
            technicien.setPassword(passwordEncoder.encode("technicien123"));
            technicien.setEmail("technicien@example.com");
            Set<Role> technicienRoles = new HashSet<>();
            technicienRoles.add(roleRepository.findByNom("ROLE_TECHNICIEN").get());
            technicien.setRoles(technicienRoles);
            userRepository.save(technicien);

            // Responsable
            User responsable = new User();
            responsable.setUsername("responsable");
            responsable.setPassword(passwordEncoder.encode("responsable123"));
            responsable.setEmail("responsable@example.com");
            Set<Role> responsableRoles = new HashSet<>();
            responsableRoles.add(roleRepository.findByNom("ROLE_RESPONSABLE").get());
            responsable.setRoles(responsableRoles);
            userRepository.save(responsable);

            // Chef de Département
            User chefDepartement = new User();
            chefDepartement.setUsername("chefdepartement");
            chefDepartement.setPassword(passwordEncoder.encode("chefdepartement123"));
            chefDepartement.setEmail("chefdepartement@example.com");
            Set<Role> chefDepartementRoles = new HashSet<>();
            chefDepartementRoles.add(roleRepository.findByNom("ROLE_CHEF_DEPARTEMENT").get());
            chefDepartement.setRoles(chefDepartementRoles);
            userRepository.save(chefDepartement);
        }
    }

    private void createRoleIfNotExists(String roleName) {
        if (!roleRepository.findByNom(roleName).isPresent()) {
            Role role = new Role(roleName);
            roleRepository.save(role);
        }
    }
}